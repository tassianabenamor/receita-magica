package br.com.infnet.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.com.infnet.myapplication.R
import br.com.infnet.myapplication.databinding.FragmentEditReceitaBinding
import br.com.infnet.myapplication.models.Receita
import br.com.infnet.myapplication.viewmodel.MainViewModel

class EditReceitaFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentEditReceitaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditReceitaBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        setupViews()
        setupObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            btnUpdateReceita.setOnClickListener {
                onAtualizarClick()
            }
        }
    }

    private fun onAtualizarClick() {
        val receita = getReceitaFromInputs()
        viewModel.updateReceita(receita)
        navUp()
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

    private fun setupObservers() {
        viewModel.selectedReceitaWithId.observe(viewLifecycleOwner){
            binding.apply {
                inputNome.setText(it.nomeReceita)
                inputTempoPreparo.setText(it.tempoPreparo)
                inputIngredientes.setText(it.ingredientes)
                inputPreparo.setText(it.modoPreparo)
            }
        }
    }

    private fun setupViews() {
        activity?.setTitle("Editar Receita")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}