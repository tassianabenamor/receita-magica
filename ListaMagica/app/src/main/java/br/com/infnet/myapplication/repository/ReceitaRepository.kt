package br.com.infnet.myapplication.repository

import br.com.infnet.myapplication.models.Receita
import br.com.infnet.myapplication.models.UserInfo
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val TAG = "ListaMagica"

class ReceitaRepository private constructor() {

    companion object {

        lateinit var auth: FirebaseAuth
        lateinit var db: FirebaseFirestore
        lateinit var colReceita: CollectionReference
        lateinit var colUserInfo: CollectionReference
        var userInfoCurrent: UserInfo? = null

        private var INSTANCE: ReceitaRepository? = null
        fun initialize(){
            if(INSTANCE == null){
                INSTANCE = ReceitaRepository()
            }
            auth = Firebase.auth
            db = Firebase.firestore

            // Aqui pega a coleção de Receitas
            colReceita = db.collection("receitas")

            // Aqui pega a coleção de UserInfo (Informações complementares do cadastro)
            colUserInfo = db.collection("userinfo")


        }

        fun get(): ReceitaRepository {
            return INSTANCE
                ?: throw IllegalStateException("ReceitaRepository deve ser iniciado.")
        }
    }

    // Metodos para Login e Cadastro
    fun getCurrentUser() = auth.currentUser

    fun isLoggedIn(): Boolean{
        if(getCurrentUser()!=null){
            return true
        }
        return false
    }

    fun getUserInfo(uid: String) : Task<DocumentSnapshot> {
        return colUserInfo.document(uid).get()
    }

    fun registerUserWithPassword(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }


    fun login(email: String, password: String): Task<AuthResult>{
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun logout(){
        auth.signOut()
    }

    fun registerUser(userInfo: UserInfo): Task<Void> {
        return colUserInfo.document(userInfo.usuarioId).set(userInfo)
    }

    // Metodos para Manter Receitas
    fun registerReceita(receita: Receita): Task<DocumentReference>{
        return colReceita.add(receita)
    }

    fun getReceitas() : Task<QuerySnapshot>{
        return colReceita.get()
    }

    fun getReceitaColecao(): CollectionReference {
        return colReceita
    }

    fun deleteReceita(id: String){
        colReceita.document(id).delete()
    }

    fun updateReceita(id: String?, receita: Receita){
        if(id!=null){
            colReceita.document(id).set(receita)
        }
    }
}