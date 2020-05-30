package su.app.presentation.ui.journal.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.journal_details_fragment.*
import su.app.R
import su.app.databinding.JournalDetailsFragmentBinding
import su.app.repository.model.journal.Dates


class JournalDetailsFragment: DaggerFragment(){

    private lateinit var adapter: JournalDetailsAdapter

    private lateinit var binding: JournalDetailsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.journal_details_fragment,container,false)
        val root = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { JournalDetailsFragmentArgs.fromBundle(it) }
        val journal = safeArgs?.dates
        val dates = safeArgs?.dates?.dates

        binding.journal = journal

        drawRecyclerView(dates!!)

        binding.detailsClose.setOnClickListener{
            closeDetails(this.view!!)
        }

        binding.executePendingBindings()
    }

    private fun drawRecyclerView(dates: List<Dates>){
        adapter = JournalDetailsAdapter(dates)
        binding.journalDetailsRecyclerview.layoutManager = GridLayoutManager(activity,4)
        binding.journalDetailsRecyclerview.adapter = adapter
    }


    private fun closeDetails(view:View){
        val backToJournal = JournalDetailsFragmentDirections.backToJournal()
        Navigation.findNavController(view).navigate(backToJournal)
    }
}
