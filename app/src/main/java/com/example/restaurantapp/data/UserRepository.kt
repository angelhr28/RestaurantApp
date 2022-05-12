package com.example.restaurantapp.data

import android.content.SharedPreferences
import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.domain.mapper.toUser
import com.example.restaurantapp.domain.model.User
import com.example.restaurantapp.utils.parseJson
import com.example.restaurantapp.utils.toJson
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val preferences: SharedPreferences,
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

    fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun logOut() {
        firebaseAuth.signOut()
    }

    suspend fun saveProfile(email: String) {
        val user = firestore.collection("users")
            .whereEqualTo("email", email).limit(1).get().await().documents.firstOrNull()?.toUser()

        val pref = preferences.edit()
        pref.putString("user", user?.toJson())
        pref.commit()
    }

    fun getProfile(): User {
        val profile = preferences.getString("user", "")
        return parseJson<User>(profile ?: "")
    }
}