package br.com.infnet.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import br.com.infnet.myapplication.repository.ReceitaRepository

class AvaliacaoViewModel: ViewModel() {

    val TAG = "ViewModel:Avaliacao"
    val repository = ReceitaRepository.get()


}