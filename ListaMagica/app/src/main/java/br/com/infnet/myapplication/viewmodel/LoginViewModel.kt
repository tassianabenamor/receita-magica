package br.com.infnet.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import br.com.infnet.myapplication.models.UserInfo
import br.com.infnet.myapplication.repository.ReceitaRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference

class LoginViewModel : ViewModel() {

    val TAG = "ViewModel"
    val repository = ReceitaRepository.get()

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    fun login(
        email: String,
        password: String
    ): Task<AuthResult>{
        return ReceitaRepository.auth.signInWithEmailAndPassword(email, password)

    }

    fun signOn(
        email: String,
        password: String
    ): Task<AuthResult>  {
        return repository.registerUserWithPassword(
            email,
            password
        )
    }

    fun registerUser(userInfo: UserInfo): Task<Void> {
        return repository.registerUser(userInfo)
    }
}