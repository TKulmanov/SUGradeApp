package su.app.presentation.ui.evaluation.transcript

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.transcript_semseter_items.view.*
import su.app.R
import su.app.databinding.TranscriptFragmentBinding
import su.app.databinding.TranscriptSemseterItemsBinding
import su.app.databinding.TranscriptSubjectItemsBinding
import su.app.repository.model.evaluation.transcript.Semester
import su.app.repository.model.evaluation.transcript.Subject
import java.lang.RuntimeException

class TranscriptAdapter (var items: List<Any>,private val infoClickListener: InfoClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        private var TYPE_SEMESTER = 0
        private var TYPE_SUBJECT = 1

        class SemesterHolder(var semesterBinding: TranscriptSemseterItemsBinding): RecyclerView.ViewHolder(semesterBinding.root) {
             fun bindSemester(semester: Any,clickListener: InfoClickListener){
                 semesterBinding.semester = semester as Semester
                 semesterBinding.semesterInfo.setOnClickListener {
                     clickListener.onInfoClicked(semester)
                 }
             }
        }

        class SubjectHolder(var subjectBinding: TranscriptSubjectItemsBinding): RecyclerView.ViewHolder(subjectBinding.root){
            fun bindSubject(subject: Any){
                subjectBinding.subject = subject as? Subject
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            if(viewType == TYPE_SUBJECT){
                val view = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<TranscriptSubjectItemsBinding>(view,R.layout.transcript_subject_items,parent,false)
                return SubjectHolder(binding)
            }else if(viewType == TYPE_SEMESTER) {
                val view = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<TranscriptSemseterItemsBinding>(view,R.layout.transcript_semseter_items,parent,false)
                return SemesterHolder(binding)
            }
            throw RuntimeException("No match for ${viewType}")
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder , position: Int) {
            if(holder is SemesterHolder)
            {
               var semesterHolder: SemesterHolder = holder
                semesterHolder.bindSemester(items[position],infoClickListener)
                return
            }else if(holder is SubjectHolder) {
                var subjectHolder: SubjectHolder = holder
                subjectHolder.bindSubject(items[position])
            }
        }

        override fun getItemCount() = items.size

        override fun getItemViewType(position: Int): Int {
            return if(items[position] is Semester)  TYPE_SEMESTER else TYPE_SUBJECT
        }


}
