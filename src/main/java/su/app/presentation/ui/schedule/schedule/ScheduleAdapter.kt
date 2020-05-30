package su.app.presentation.ui.schedule.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.ScheduleItemBinding
import su.app.repository.model.schedule.Schedule

class ScheduleAdapter(private var schedule: List<Schedule>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder>(){

    class ScheduleHolder(val binding: ScheduleItemBinding,val context: Context?): RecyclerView.ViewHolder(binding.root){
        var colors = context?.resources!!.getIntArray(R.array.schedule_color)
        fun bind(schedule: Schedule,position: Int) {
            binding.schedule = schedule

            if(schedule.id !=  0) {
                binding.scheduleItem.setBackgroundColor(colors[position])
                return
            }
            binding.scheduleItem.setBackgroundColor(0)
        }
    }

    fun setScheduleByDay(schedule: List<Schedule>){
        this.schedule = schedule
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ScheduleItemBinding>(view, R.layout.schedule_item,parent,false)
        return ScheduleHolder(
            binding,
            parent.context
        )
    }

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        val schedule =schedule[position]
        holder.bind(schedule,position)
    }

    override fun getItemCount() = schedule.size
}