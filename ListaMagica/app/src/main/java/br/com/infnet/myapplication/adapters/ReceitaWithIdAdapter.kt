package br.com.infnet.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.infnet.myapplication.databinding.ReceitaListItemBinding
import br.com.infnet.myapplication.models.ReceitaWithId

class ReceitaWithIdAdapter(val listener: ReceitaWithIdListener) :
    ListAdapter<
            ReceitaWithId,
            ReceitaWithIdAdapter.ViewHolder
            >(ReceitaWithIdDiffCallback()) {

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
        val listener: ReceitaWithIdListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReceitaWithId, position: Int){
            binding.apply {
                receitaNome.text = item.nomeReceita
                tempoPreparo.text = item.tempoPreparo
                ivEdit.setOnClickListener{
                    listener.onEditClick(item)
                }
                ivDelete.setOnClickListener {
                    listener.onDeleteClick(item)
                }
                ivView.setOnClickListener {
                    listener.onViewClick(item)
                }
            }
        }

        companion object{
            fun from(parent: ViewGroup, listener: ReceitaWithIdListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ReceitaListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }
}

class ReceitaWithIdDiffCallback : DiffUtil.ItemCallback<ReceitaWithId>() {
    override fun areItemsTheSame(oldItem: ReceitaWithId, newItem: ReceitaWithId): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: ReceitaWithId, newItem: ReceitaWithId): Boolean {
        return oldItem == newItem
    }
}

interface ReceitaWithIdListener{
    fun onEditClick(receita: ReceitaWithId)
    fun onDeleteClick(receita: ReceitaWithId)
    fun onViewClick(receita: ReceitaWithId)
}