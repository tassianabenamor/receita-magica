package br.com.infnet.myapplication.application

import android.app.Application
import br.com.infnet.myapplication.repository.ReceitaRepository

class ReceitaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ReceitaRepository.initialize()
    }
}