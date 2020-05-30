package su.app.presentation.ui.schedule.exam

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.ExamItemBinding
import su.app.repository.model.schedule.Exam

class ExamAdapter(var exams: List<Exam>) : RecyclerView.Adapter<ExamAdapter.ExamHolder>(){

    class ExamHolder(val binding: ExamItemBinding, val context: Context?): RecyclerView.ViewHolder(binding.root){
        private val colors = context?.resources!!.getIntArray(R.array.exam_color)
        private val dateColors = context?.resources!!.getIntArray(R.array.exam_text_color)
        fun bind(exam: Exam, position: Int) {
            binding.exam = exam
            binding.examCircle.setBackgroundColor(colors[position])
            binding.examTime.setBackgroundColor(colors[position])
            binding.examDate.setTextColor(dateColors[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ExamItemBinding>(view, R.layout.exam_item,parent,false)
        return ExamHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ExamHolder, position: Int) {
        val exam =exams[position]
        holder.bind(exam,position)
    }

    override fun getItemCount() = exams.size
}