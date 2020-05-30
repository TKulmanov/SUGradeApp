package su.app.presentation.ui.umkd.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
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
import su.app.databinding.CategoryFragmentBinding
import su.app.domain.CategoryViewModel
import su.app.presentation.injectViewModel
import su.app.repository.model.umkd.Category
import javax.inject.Inject

class CategoryFragment : DaggerFragment(), CategoryClickListener {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var categoryViewModel: CategoryViewModel

    private lateinit var binding: CategoryFragmentBinding

    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        categoryViewModel = injectViewModel(viewModelFactory)

        binding = DataBindingUtil.inflate(inflater,R.layout.category_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = arguments?.let { CategoryFragmentArgs.fromBundle(it) }

        categoryViewModel.disciplineId.postValue(args?.disciplineId)

        observeData()
        drawRecyclerView()
    }

    private fun drawRecyclerView(){
        adapter = CategoryAdapter(listOf(),this)
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = DividerItemDecoration(this.context , DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(AppCompatResources.getDrawable(this.context!!, R.drawable.divider)!!)
        binding.categoryRecyclerView.addItemDecoration(itemDecoration)
        binding.categoryRecyclerView.adapter = adapter
    }

    private fun observeData() = GlobalScope.launch(Dispatchers.Main){
        val categories = categoryViewModel.categories.await()
        categories.observe(this@CategoryFragment, Observer { categoriesList ->
            adapter.categories = categoriesList
            adapter.notifyDataSetChanged()
        })

    }

    override fun onItemClicked(category: Category, view: View) {
        showFiles(category,view)
    }

    private fun showFiles(category: Category,view: View){
        val actionDetails = CategoryFragmentDirections?.toFiles(category)
        Navigation.findNavController(view).navigate(actionDetails)
    }

}
