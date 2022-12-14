package br.com.infnet.myapplication.models

data class ReceitaWithId(
    val nomeReceita: String = "",
    val tempoPreparo: String = "",
    val ingredientes: String = "",
    val modoPreparo: String = "",
    var id: String = ""
)
