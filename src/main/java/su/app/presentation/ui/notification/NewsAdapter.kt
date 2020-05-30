package su.app.presentation.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.NewsItemBinding
import su.app.repository.model.notification.News

class NewsAdapter(var news: List<News>) : RecyclerView.Adapter<NewsAdapter.NewsHolder>(){

    class NewsHolder(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(news: News) {
            binding.news = news
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<NewsItemBinding>(view, R.layout.news_item,parent,false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news =news[position]
        holder.bind(news)
    }

    override fun getItemCount() = news.size
}