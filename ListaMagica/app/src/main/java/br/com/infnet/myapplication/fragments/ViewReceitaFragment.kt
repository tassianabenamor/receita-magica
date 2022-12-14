package br.com.infnet.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import br.com.infnet.myapplication.R
import br.com.infnet.myapplication.adapters.ReceitaWithIdAdapter
import br.com.infnet.myapplication.adapters.ReceitaWithIdListener
import br.com.infnet.myapplication.databinding.FragmentViewReceitaBinding
import br.com.infnet.myapplication.models.ReceitaWithId
import br.com.infnet.myapplication.viewmodel.MainViewModel


class ViewReceitaFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentViewReceitaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewReceitaBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        setupViews()
        setupListener()
        setupRecyclerView()
        setupObservers()
    }

    val adapter = ReceitaWithIdAdapter(
        object : ReceitaWithIdListener {
            override fun onEditClick(receita: ReceitaWithId) {
                viewModel.setSelectedReceitaWithId(receita)
                nav(R.id.action_receitaFragment_to_editReceitaFragment)
            }
            override fun onDeleteClick(receita: ReceitaWithId) {
                viewModel.deleteReceita(receita.id)
            }
            override fun onViewClick(receita: ReceitaWithId){
                viewModel.setSelectedReceitaWithId(receita)
                nav(R.id.action_receitaFragment_to_viewReceitaFragment)
            }
        }
    )

    private fun setupRecyclerView() {
        binding.rvAvaliacoes.adapter = adapter
    }

    private fun setupListener() {
        binding.btnAvaliar.setOnClickListener {
            nav(R.id.action_viewReceitaFragment_to_registerAvaliacaoFragment)
        }
    }

    private fun setupObservers() {
        viewModel.selectedReceitaWithId.observe(viewLifecycleOwner){
            binding.apply {
                tvNomeReceita.text = it.nomeReceita
                tvTempoPreparo.text = it.tempoPreparo
                tvIngredientes.text = it.ingredientes
                tvModoPreparo.text = it.modoPreparo
            }
        }
    }

    private fun setupViews() {
        activity?.setTitle("Detalhes")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}