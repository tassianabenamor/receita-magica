package br.com.infnet.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.infnet.myapplication.databinding.ReceitaListItemBinding
import br.com.infnet.myapplication.models.Receita

class ReceitaAdapter(val listener: ReceitaListener) :
        ListAdapter<
                Receita,
                ReceitaAdapter.ViewHolder
                >(ReceitaDiffCallback()) {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.bind(item, position)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            return ViewHolder.from(parent, listener)
        }

        class ViewHolder private constructor(
            val binding: ReceitaListItemBinding,
            val listener: ReceitaListener
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Receita, position: Int) {
                binding.apply {
                    receitaNome.text = item.nomeReceita
                    tempoPreparo.text = item.tempoPreparo
                    ivEdit.setOnClickListener {
                        listener.onEditClick(item)
                    }
                    ivDelete.setOnClickListener {
                        listener.onDeleteClick(item)
                    }
                }
            }
            companion object {
                fun from(parent: ViewGroup, listener: ReceitaListener): ViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ReceitaListItemBinding.inflate(
                        layoutInflater, parent, false
                    )
                    return ViewHolder(binding, listener)
                }
            }
        }
}


class ReceitaDiffCallback : DiffUtil.ItemCallback<Receita>() {
    override fun areItemsTheSame(oldItem: Receita, newItem: Receita): Boolean {
        return oldItem.nomeReceita == newItem.nomeReceita
    }
    override fun areContentsTheSame(oldItem: Receita, newItem: Receita): Boolean {
        return oldItem == newItem
    }
}

interface ReceitaListener {
    fun onEditClick(receita: Receita)
    fun onDeleteClick(receita: Receita)
}

