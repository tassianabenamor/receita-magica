package br.com.infnet.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.infnet.myapplication.models.Receita
import br.com.infnet.myapplication.models.ReceitaWithId
import br.com.infnet.myapplication.models.UserInfo
import br.com.infnet.myapplication.repository.ReceitaRepository
import br.com.infnet.myapplication.repository.TAG
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject

class MainViewModel: ViewModel() {

    val TAG = "ViewModel"
    val repository = ReceitaRepository.get()

    // Login
    fun getCurrentUserEmail(): String{
        return repository.getCurrentUser()?.email ?: "Email não encontrado"
    }

    fun logout(){
        repository.logout()
    }


    // Receita
    fun registerReceita(receita: Receita): Task<DocumentReference>{
        return repository.registerReceita(receita)
    }

    fun observeColecaoReceitas() {
        repository.getReceitaColecao()
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "listen:error", e)
                    return@addSnapshotListener
                }
                val listaInput = mutableListOf<ReceitaWithId>()
                val listaRemocao = mutableListOf<String>()
                val listaModificacao = mutableListOf<ReceitaWithId>()
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            val receita = dc.document.toObject<Receita>()
                            val id = dc.document.id
                            val receitaWithId = receitaToReceitaWithId(receita, id)
                            Log.i(TAG, "turmaComId: ${receitaWithId}")
                            listaInput.add(receitaWithId)
                        }
                        DocumentChange.Type.MODIFIED -> {
                            val receita = dc.document.toObject<Receita>()
                            val id = dc.document.id
                            val receitaWithId = receitaToReceitaWithId(receita, id)
                            Log.i(TAG, "Modificacao - turmaComId: ${receitaWithId}")
                            listaModificacao.add(receitaWithId)
                        }
                        DocumentChange.Type.REMOVED -> {
                            val id = dc.document.id
                            Log.i(TAG, "id removido: ${id}")
                            listaRemocao.add(dc.document.id)
                        }
                    }
                }
                addListaToReceitaWithId(listaInput)
                removeFromReceitaWithId(listaRemocao)
                modifyInReceitaWithId(listaModificacao)
            }
    }

    private val _receitaWithId = MutableLiveData<List<ReceitaWithId>>()
    val receitaWithId: LiveData<List<ReceitaWithId>> = _receitaWithId

    fun addListaToReceitaWithId(listaInput: List<ReceitaWithId>) {
        val listaOld = receitaWithId.value
        val listaNew = mutableListOf<ReceitaWithId>()

        listaOld?.forEach {
            listaNew.add(it)
        }

        listaInput.forEach {
            listaNew.add(it)
        }
        setReceitaWithId(listaNew)
    }

    private fun removeFromReceitaWithId(listaRemocao: List<String>) {
        val listOld = receitaWithId.value
        val listNew = mutableListOf<ReceitaWithId>()
        Log.i(TAG, "listaRemocao: ${listaRemocao}")
        if(listaRemocao.isNotEmpty()){
            listOld?.forEach {
                Log.i(TAG, "item da lista Antiga: ${it.id}")
                if (it.id in listaRemocao) {
                    Log.i(TAG, "item ${it.id} está dentro da listaRemocao")
                } else {
                    Log.i(TAG, "item ${it.id} _NÃO_ está dentro da listaRemocao")
                    listNew.add(it)
                }
            }
            setReceitaWithId(listNew)
        }
    }

    private fun modifyInReceitaWithId(listaModificacao: List<ReceitaWithId>) {
        Log.i(TAG, "listaModificacao: ${listaModificacao}")
        if (listaModificacao.isNotEmpty()) {
            for (itemModificado in listaModificacao) {
                modifyItemInListaReceitaWithId(itemModificado)
            }
        }
    }

    fun modifyItemInListaReceitaWithId(itemModificado: ReceitaWithId) {
        val listOld = receitaWithId.value
        val listNew = mutableListOf<ReceitaWithId>()

        listOld?.forEach { itemDaLista ->
            if (itemModificado.id == itemDaLista.id) {
                listNew.add(itemModificado)
            } else {
                listNew.add(itemDaLista)
            }
        }
        setReceitaWithId(listNew)
    }

    fun setReceitaWithId(value: List<ReceitaWithId>) {
        _receitaWithId.postValue(value)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private val _selectedReceitaWithId = MutableLiveData<ReceitaWithId>()
    val selectedReceitaWithId: LiveData<ReceitaWithId> = _selectedReceitaWithId

    private fun receitaToReceitaWithId(receita: Receita, id: String): ReceitaWithId {
        return ReceitaWithId(
            nomeReceita = receita.nomeReceita,
            tempoPreparo = receita.tempoPreparo,
            ingredientes = receita.ingredientes,
            modoPreparo = receita.modoPreparo,
            id = id
        )
    }

    fun getReceitas(): List<Receita> {
        val lista = mutableListOf<Receita>()
        repository.getReceitas()
            .addOnSuccessListener{ documents ->
                for(document in documents){
                    val receita = document.toObject<Receita>()
                    lista.add(receita)
                    Log.i(TAG, "document: ${document}")
                    Log.i(TAG, "Receita: ${receita}")
                }
            }
            .addOnFailureListener{ exception ->
                Log.w(TAG, "Error ao buscar registros", exception)
            }
        return lista
    }

    fun deleteReceita(id: String) {
        repository.deleteReceita(id)
    }

    fun setSelectedReceitaWithId(value: ReceitaWithId) {
        _selectedReceitaWithId.postValue(value)
    }

    private val _receitas = MutableLiveData<List<Receita>>()
    val receitas: LiveData<List<Receita>> = _receitas

    fun setReceitas(value: List<Receita>) {
        _receitas.postValue(value)
    }

    fun updateReceita(receita: Receita) {
        repository.updateReceita(selectedReceitaWithId.value?.id, receita)
    }

    init {
        observeColecaoReceitas()
    }

}