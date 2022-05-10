package com.example.restaurantapp.data

import android.util.Log
import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.utils.exceptionFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) {

    fun checkInAuth(userModel: UserModel): Flow<String> {
        return callbackFlow {
            firebaseAuth.createUserWithEmailAndPassword(userModel.email, userModel.password)
                .addOnCompleteListener { task ->
                    task.exception?.let {
                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        if (it is FirebaseAuthInvalidCredentialsException) {
                            cancel("El correo no es valido o no existe.", it)
                            return@addOnCompleteListener
                        }
                        if (it is FirebaseAuthUserCollisionException) {
                            cancel("El correo ya se encuentra registrado en la plataforma.", it)
                            return@addOnCompleteListener
                        }
                    } ?: kotlin.run {
                        currentUser()?.let { offer(it.uid) }
                    }
                }
            awaitClose {}
        }
    }

    fun checkInFirestore(userModel: UserModel): Flow<String> {
        val user = hashMapOf(
            "full_name" to userModel.fullName,
            "phone" to userModel.phone,
            "emial" to userModel.email,
            "password" to userModel.password
        )

        return callbackFlow {
            firestore.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    offer(documentReference.id)
                }
                .addOnFailureListener { e ->
                    cancel("Error adding document", e)
                }
            awaitClose { }
        }

    }

    fun logIn(email: String, password: String): Flow<String> {
        return callbackFlow {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    exceptionFirebase(task.exception)?.let {
                        cancel(it, task.exception)
                        return@addOnCompleteListener
                    } ?: kotlin.run {
                        currentUser()?.let { offer(it.uid) }
                    }
                }
            awaitClose {}
        }
    }

    fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun logOut() {
        firebaseAuth.signOut()
    }

    suspend fun getProfile(email: String) {
        val a = firestore.collection("users")
            .whereEqualTo("email", email).get().addOnSuccessListener {
                for (document in it) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }

        val b = firestore.collection("users")
            .whereEqualTo("emial", email).get().await().documents.firstOrNull()?.data

        Log.e("TAG", "getProfile: $b")
    }
}