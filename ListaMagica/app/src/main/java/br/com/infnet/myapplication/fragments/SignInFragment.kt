package br.com.infnet.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.activityViewModels
import br.com.infnet.myapplication.MainActivity
import br.com.infnet.myapplication.R
import br.com.infnet.myapplication.databinding.FragmentSignInBinding
import br.com.infnet.myapplication.viewmodel.LoginViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class SignInFragment : Fragment() {

    val viewModel by activityViewModels<LoginViewModel>()
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setup() {
        setupView()
        setupClickListeners()
    }

    private fun setupView() {
        activity?.setTitle("Receita Mágica")
    }

    private fun setupClickListeners() {
        binding.apply {
            btnSignIn.setOnClickListener {
                onSignInClick()
            }
            btnSignOn.setOnClickListener {
                onSignOnClick()
            }
        }
    }

    private fun onSignOnClick() {
        nav(R.id.action_signInFragment_to_signOnFragment)
    }

    private fun onSignInClick() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        signIn(email, password)

    }

    private fun signIn(email: String, password: String){
        viewModel.login(email, password)
            .addOnSuccessListener {
                toast("Logado com Sucesso")
                startMainActivity()
            }
            .addOnFailureListener {
                toast("Falha ao Logar\n${it.message}")
            }
    }

    fun startMainActivity(){
        startActivity(Intent(requireContext(), MainActivity::class.java))
        activity?.finish()
    }

}