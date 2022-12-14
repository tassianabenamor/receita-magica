package br.com.infnet.myapplication.models

import com.google.gson.annotations.SerializedName

class Avaliacao{

    @SerializedName("avaliacao_id")
    var avaliacaoId: Int = 0

    @SerializedName("receita_id")
    var receitaId: String = ""

    @SerializedName("usuario_id")
    var usuarioId: String = ""

    @SerializedName("nota")
    var nota: Int = 0

    @SerializedName("descricao")
    var descricao: String = ""

    @SerializedName("usuario_nome")
    var usuarioNome: String = ""
}