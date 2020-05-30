package su.app.presentation.ui.journal.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.JournalItemsBinding
import su.app.repository.model.journal.Journal

class JournalAdapter(var journals: List<Journal>,private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<JournalAdapter.JournalHolder>(){

    class JournalHolder(val binding: JournalItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(journal: Journal,clickListener: ItemClickListener) {
            binding.journal = journal
            itemView.setOnClickListener {
                clickListener.onItemClicked(journal,itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<JournalItemsBinding>(view, R.layout.journal_items,parent,false)
        return JournalHolder(binding)
    }

    override fun onBindViewHolder(holder: JournalHolder, position: Int) {
        val journal =journals[position]
        holder.bind(journal,itemClickListener)
    }

    override fun getItemCount() =journals.size
}