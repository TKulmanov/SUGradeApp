package su.app.presentation.ui.journal.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.JournalDetailsItemsBinding
import su.app.repository.model.journal.Dates


class JournalDetailsAdapter(private var dates: List<Dates>) : RecyclerView.Adapter<JournalDetailsAdapter.JournalHolder>(){

        class JournalHolder(val binding: JournalDetailsItemsBinding): RecyclerView.ViewHolder(binding.root){

            fun bind(date: Dates) {
                binding.setDate(date)
                if(date.attended) binding.attendanceImg.setBackgroundResource(R.drawable.attended_true_bg)
                    else binding.attendanceImg.setBackgroundResource(R.drawable.attended_false_bg)
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalHolder {
            val view = LayoutInflater.from(parent.context)
            val binding = JournalDetailsItemsBinding.inflate(view)
            return JournalHolder(binding)
        }

        override fun onBindViewHolder(holder: JournalHolder, position: Int) {
            holder.bind(dates[position])
        }

        override fun getItemCount() = dates.size

}
