package br.com.infnet.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.infnet.myapplication.MainActivity
import br.com.infnet.myapplication.databinding.ActivityLoginBinding
import br.com.infnet.myapplication.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        if(viewModel.isLoggedIn()){
            startMainActivity()
        }
    }

    fun startMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}