package com.example.restaurantapp.data.network

import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.domain.mapper.toUser
import com.example.restaurantapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserService @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) {
    suspend fun checkInAuth(userModel: UserModel) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(userModel.email, userModel.password).await()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw Exception("El correo no es valido o no existe.")
        } catch (e: FirebaseAuthUserCollisionException) {
            throw  Exception("El correo ya se encuentra registrado en la plataforma.")
        }
    }

    suspend fun checkInFirestore(userModel: UserModel) {
        firestore.collection("users")
            .add(userModel.toUser()).await()
    }

    suspend fun logIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    fun logOut() {
        firebaseAuth.signOut()
    }

    suspend fun saveProfile(email: String): User? {
        return firestore.collection("users")
            .whereEqualTo("email", email).limit(1).get().await().documents.firstOrNull()?.toUser()
    }

    fun verifyLogin(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}