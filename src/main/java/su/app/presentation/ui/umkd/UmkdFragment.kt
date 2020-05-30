package su.app.presentation.ui.umkd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import su.app.R
import su.app.ViewModelFactory
import su.app.databinding.UmkdFragmentBinding
import su.app.domain.UmkdViewModel
import su.app.presentation.injectViewModel
import su.app.presentation.ui.umkd.category.CategoryFragmentDirections
import su.app.repository.model.umkd.Discipline
import javax.inject.Inject

class UmkdFragment : DaggerFragment(),DisciplineClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var umkdViewModel: UmkdViewModel

    private lateinit var binding: UmkdFragmentBinding

    private lateinit var adapter: DisciplineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        umkdViewModel = injectViewModel(viewModelFactory)

        binding = DataBindingUtil.inflate(inflater,R.layout.umkd_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
        drawRecyclerView()
    }

    private fun drawRecyclerView(){
        adapter = DisciplineAdapter(listOf(),this)
        binding.disciplineRecyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = DividerItemDecoration(this.context , DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(this.context!!,R.drawable.divider)!!)
        binding.disciplineRecyclerView.addItemDecoration(itemDecoration)
        binding.disciplineRecyclerView.adapter = adapter
    }



    private fun observeData() = GlobalScope.launch(Dispatchers.Main) {
        val disciplines = umkdViewModel.disciplines.await()
        disciplines.observe(this@UmkdFragment, Observer { disciplinesList ->
            if (disciplinesList == null) return@Observer
            println(disciplinesList)
            adapter.disciplines = disciplinesList
            adapter.notifyDataSetChanged()
        })
    }

    private fun showCategories(disciplineId: String,view: View){
        val actionDetails = UmkdFragmentDirections?.categories(disciplineId)
        Navigation.findNavController(view).navigate(actionDetails)
    }

    override fun onItemClicked(disciplineId: String,view: View) {
        showCategories(disciplineId,view)
    }

}
