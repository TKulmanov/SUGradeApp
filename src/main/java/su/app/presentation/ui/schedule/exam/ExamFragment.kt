package su.app.presentation.ui.schedule.exam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import su.app.R
import su.app.ViewModelFactory
import su.app.databinding.ExamFragmentBinding
import su.app.domain.ExamViewModel
import su.app.presentation.injectViewModel
import javax.inject.Inject

class ExamFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var examViewModel: ExamViewModel

    private lateinit var binding: ExamFragmentBinding

    private lateinit var adapter: ExamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        examViewModel = injectViewModel(viewModelFactory)

        binding = DataBindingUtil.inflate(inflater,R.layout.exam_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
        drawRecyclerView()
    }

    private fun observeData() = GlobalScope.launch(Dispatchers.Main){
        val exam = examViewModel.exam.await()

        exam.observe(this@ExamFragment, Observer{ examsList->
            if(examsList==null) return@Observer
            adapter.exams = examsList
            adapter.notifyDataSetChanged()
        })
    }

    private fun drawRecyclerView(){
        adapter = ExamAdapter(listOf())
        binding.examRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.examRecyclerView.adapter = adapter
    }

}
