package br.com.infnet.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.infnet.myapplication.R
import br.com.infnet.myapplication.databinding.FragmentReceitaBinding
import br.com.infnet.myapplication.adapters.ReceitaWithIdAdapter
import br.com.infnet.myapplication.adapters.ReceitaWithIdListener
import br.com.infnet.myapplication.models.ReceitaWithId
import br.com.infnet.myapplication.viewmodel.MainViewModel

class ReceitaFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentReceitaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReceitaBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup(){
        setupViews()
        setupClickListeners()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupClickListeners() {
        binding.btnRegisterReceita.setOnClickListener {
            nav(R.id.action_receitaFragment_to_registerReceitaFragment)
        }
    }

    private fun setupViews() {
        activity?.setTitle("Receitas")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        binding.rvReceitas.adapter = adapter
        binding.rvReceitas.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    private fun setupObservers() {
        viewModel.receitaWithId.observe(viewLifecycleOwner) {
            atualizaRecyclerView(it)
        }
    }

    fun atualizaRecyclerView(lista: List<ReceitaWithId>) {
        adapter.submitList(lista)
        binding.rvReceitas.adapter = adapter
    }

}