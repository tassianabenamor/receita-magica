package br.com.infnet.myapplication.models

data class UserInfo(
    val usuarioNome: String,
    val cep: String,
    val endereco: String,
    val numero: String,
    val complemento: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    var usuarioId: String = ""
    ){
    fun setUserId(uid: String){
        this.usuarioId = uid
    }
}
