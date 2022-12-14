package br.com.infnet.myapplication.repository

import br.com.infnet.myapplication.models.Avaliacao
import br.com.infnet.myapplication.models.Endereco
import br.com.infnet.myapplication.models.ReturnApiDefault
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class AvaliavaoApiService {

    companion object{

        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://src.kicol.com.br/"

        private fun getRetrofitInstance(): Retrofit{
            val http = OkHttpClient.Builder()
            if(!::INSTANCE.isInitialized){
                synchronized(AvaliavaoApiService::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(http.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun createAvaliacaoService(): AvaliacaoService {
            return getRetrofitInstance().create(AvaliacaoService::class.java)
        }
    }
}

interface AvaliacaoService {

    @GET("api/run.php?=rid={rid}")
    fun getAvaliacaoByReceitaId(@Path("rid") rid: String):Call<Avaliacao>

    @GET("api/run.php?=rid={rid}")
    fun getAvaliacaoById(@Path("aid") aid: String):Call<MutableList<Avaliacao>>

    @POST("api/run.php")
    @FormUrlEncoded
    fun insertAvaliacao(
        @Field("action") action: String,
        @Field("receita_id") receitaId: String,
        @Field("usuario_id") usuarioId: String,
        @Field("nota") nota: Int,
        @Field("descricao") descricao: String,
        @Field("usuario_nome") usuarioNome: String
    ): Call<ReturnApiDefault>

    @POST("api/run.php")
    @FormUrlEncoded
    fun updateAvaliacao(
        @Field("action") action: String,
        @Field("avaliacao_id") avaliacaoId: String,
        @Field("nota") nota: Int,
        @Field("descricao") descricao: String
    ): Call<ReturnApiDefault>

    @POST("api/run.php")
    @FormUrlEncoded
    fun deleteAvaliacao(
        @Field("action") action: String,
        @Field("avaliacao_id") avaliacaoId: String
    ): Call<ReturnApiDefault>

}