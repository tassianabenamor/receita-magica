package br.com.infnet.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.com.infnet.myapplication.R
import br.com.infnet.myapplication.databinding.FragmentRegisterAvaliacaoBinding
import br.com.infnet.myapplication.viewmodel.MainViewModel

class RegisterAvaliacaoFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentRegisterAvaliacaoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterAvaliacaoBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view

    }

    private fun setup() {
        setupViews()
        setupListener()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.selectedReceitaWithId.observe(viewLifecycleOwner){
            binding.apply {
                tvNomeReceita.text = it.nomeReceita
            }
        }
    }

    private fun setupListener() {
        binding.btnRegisterAvaliacao.setOnClickListener {
            var receitaId: String = ""
            viewModel.selectedReceitaWithId.observe(viewLifecycleOwner){
                receitaId = it.id
            }
            binding.inputUsuarioNome.text.toString()
            binding.inputDescricao.text.toString()
            binding.rtbNota.toString()
        }

    }

    private fun setupViews() {

    }

}