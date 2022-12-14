package br.com.infnet.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.com.infnet.myapplication.databinding.FragmentRegisterReceitaBinding
import br.com.infnet.myapplication.models.Receita
import br.com.infnet.myapplication.viewmodel.MainViewModel

class RegisterReceitaFragment : Fragment() {

    val viewModel by activityViewModels<MainViewModel>()
    private var _binding: FragmentRegisterReceitaBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterReceitaBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegisterReceita.setOnClickListener {
            val receita = getReceitaFromInputs()
            viewModel.registerReceita(receita)
                .addOnSuccessListener {
                    toast("${receita.nomeReceita} Cadastrada com sucesso.")
                }
                .addOnFailureListener {
                    //Log.w(Companion.TAG, "setupClickListeners: Failure")
                    toast("Falha ao cadastrar.")
                }
        }
    }

    private fun getReceitaFromInputs(): Receita {
        binding.apply {
            return Receita(
                nomeReceita = inputNome.text.toString(),
                tempoPreparo = inputTempoPreparo.text.toString(),
                ingredientes = inputIngredientes.text.toString(),
                modoPreparo = inputPreparo.text.toString()
            )
        }
    }

    private fun setupViews() {
        activity?.setTitle("Cadastrar Receita")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ListaMagica"
    }
}