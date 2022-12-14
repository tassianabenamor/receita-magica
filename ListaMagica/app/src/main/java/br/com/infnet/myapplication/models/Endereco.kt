package br.com.infnet.myapplication.models

import com.google.gson.annotations.SerializedName

class Endereco {

    @SerializedName("cep")
    var cep: String = ""
    @SerializedName("logradouro")
    var logradouro: String = ""
    @SerializedName("complemento")
    var complemento: String = ""
    @SerializedName("bairro")
    var bairro: String = ""
    @SerializedName("localidade")
    var cidade: String = ""
    @SerializedName("uf")
    var estado: String = ""
    @SerializedName("ibge")
    var ibge: String = ""
    @SerializedName("gia")
    var gia: String = ""
    @SerializedName("ddd")
    var ddd: Int = 0
    @SerializedName("siafi")
    var siafi: Int = 0
}