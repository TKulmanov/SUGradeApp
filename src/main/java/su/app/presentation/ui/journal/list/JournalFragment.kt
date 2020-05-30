package su.app.presentation.ui.journal.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import su.app.R
import su.app.ViewModelFactory
import su.app.databinding.JournalFragmentsBinding
import su.app.presentation.injectViewModel
import su.app.domain.JournalViewModel
import su.app.repository.model.journal.Journal
import javax.inject.Inject

class JournalFragment: DaggerFragment(),ItemClickListener{


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private  lateinit var  journalViewModel: JournalViewModel

    private lateinit var binding: JournalFragmentsBinding

    private  lateinit var adapter: JournalAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        journalViewModel = injectViewModel(viewModelFactory)

        binding = DataBindingUtil.inflate(inflater,R.layout.journal_fragments,container,false)

        val root = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
        drawRecyclerView()
    }

    private fun drawRecyclerView() {
        adapter = JournalAdapter(listOf(),this)
        binding.journalRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.journalRecyclerview.adapter = adapter
    }


    private fun observeData() = GlobalScope.launch(Dispatchers.Main) {
        val journals = journalViewModel.attestation.await()

        journals.observe(this@JournalFragment, Observer { journalList ->
            if (journalList == null) return@Observer
            adapter.journals = journalList
            adapter.notifyDataSetChanged()
        })
    }

    private fun showJournalDetails(journal:Journal,view: View){
        val actionDetails = JournalFragmentDirections?.journalDetails(journal)
        val extras = FragmentNavigatorExtras(

        )
        Navigation.findNavController(view).navigate(actionDetails,extras)
    }

    override fun onItemClicked(journal: Journal,view: View) {
        showJournalDetails(journal,view)
    }
}
