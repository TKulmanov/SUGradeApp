package su.app.presentation.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import su.app.databinding.NewsFragmentBinding
import su.app.presentation.injectViewModel
import su.app.domain.NewsViewModel
import su.app.repository.model.notification.News
import javax.inject.Inject

class NewsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var newsViewModel: NewsViewModel

    private lateinit var binding: NewsFragmentBinding

    private  lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newsViewModel = injectViewModel(viewModelFactory)
        binding = DataBindingUtil.inflate(inflater,R.layout.news_fragment,container,false)
        val root = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeData()
        drawRecyclerView()
    }


    private fun observeData() = GlobalScope.launch(Dispatchers.Main) {
        val news = newsViewModel.news.await()

        news.observe(this@NewsFragment, Observer { newsList ->
            if (newsList == null) return@Observer
            adapter.news = newsList
            adapter.notifyDataSetChanged()
        })
    }

    private fun drawRecyclerView() {
        adapter = NewsAdapter(listOf())
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = DividerItemDecoration(this.context , DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(AppCompatResources.getDrawable(this.context!!, R.drawable.divider)!!)
        binding.newsRecyclerView.addItemDecoration(itemDecoration)
        binding.newsRecyclerView.adapter = adapter
    }
}
