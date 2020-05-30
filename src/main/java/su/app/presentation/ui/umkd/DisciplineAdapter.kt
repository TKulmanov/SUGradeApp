package su.app.presentation.ui.umkd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.DisciplineItemBinding
import su.app.presentation.ui.journal.list.ItemClickListener
import su.app.repository.model.umkd.Discipline

class DisciplineAdapter(var disciplines: List<Discipline>,private val itemClickListener: DisciplineClickListener) : RecyclerView.Adapter<DisciplineAdapter.DisciplineHolder>(){

    class DisciplineHolder(val binding: DisciplineItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(discipline: Discipline,clickListener: DisciplineClickListener) {
            binding.discipline = discipline
            itemView.setOnClickListener {
                clickListener.onItemClicked(discipline.disciplineId,itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisciplineHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DisciplineItemBinding>(view, R.layout.discipline_item,parent,false)
        return DisciplineHolder(binding)
    }

    override fun onBindViewHolder(holder: DisciplineHolder, position: Int) {
        val discipline =disciplines[position]
        holder.bind(discipline,itemClickListener)
    }

    override fun getItemCount() =disciplines.size
}