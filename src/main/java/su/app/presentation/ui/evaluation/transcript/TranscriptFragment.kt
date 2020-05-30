package su.app.presentation.ui.evaluation.transcript

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import su.app.R
import su.app.ViewModelFactory
import su.app.databinding.TranscriptDialogFragmentBindingImpl
import su.app.databinding.TranscriptFragmentBinding
import su.app.presentation.injectViewModel
import su.app.domain.TranscriptViewModel
import su.app.repository.model.evaluation.transcript.Semester
import su.app.repository.model.evaluation.transcript.Transcript
import javax.inject.Inject

class TranscriptFragment : DaggerFragment(),InfoClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private  lateinit var  transcriptViewModel: TranscriptViewModel

    private lateinit var adapter: TranscriptAdapter

    private lateinit var binding: TranscriptFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        transcriptViewModel = injectViewModel(viewModelFactory)

        binding = DataBindingUtil.inflate(inflater,R.layout.transcript_fragment,container,false)
        val root = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
        drawRecyclerView()
    }

    private fun observeData(){
        GlobalScope.launch(Dispatchers.Main) {
            val transcript = transcriptViewModel.transcript.await()

            transcript.observe(this@TranscriptFragment, Observer { transcript ->
                if(transcript == null) return@Observer
                castTranscript(transcript)
            })

        }
    }

    private fun castTranscript(transcript: Transcript){
        val semesters = transcript.semesters
        var array =  arrayListOf<Any>()
        semesters.forEach {
            array.add(it)
            array.addAll(it.subjects)
        }
        adapter.items = array
        adapter.notifyDataSetChanged()
    }

    private fun drawRecyclerView(){
        adapter = TranscriptAdapter(listOf(),this)
        binding.transcriptList.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = DividerItemDecoration(this.context , DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(AppCompatResources.getDrawable(this.context!!, R.drawable.divider)!!)
        binding.transcriptList.addItemDecoration(itemDecoration)
        binding.transcriptList.adapter = adapter
    }

    override fun onInfoClicked(semester: Semester) {
        val binding: TranscriptDialogFragmentBindingImpl = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.transcript_dialog_fragment,null,false)
        binding.semester = semester

        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)

        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.3).toInt()

        dialog.window?.setLayout(width,height)
        dialog.window?.setBackgroundDrawableResource(R.drawable.transcript_dialog_bg)
        dialog.show()
    }
}

