package su.app.presentation.ui.umkd.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.CategoryItemBinding
import su.app.repository.model.umkd.Category

class CategoryAdapter(var categories: List<Category>, private val itemClickListener: CategoryClickListener) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    class CategoryHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, clickListener: CategoryClickListener) {
            binding.category = category
            itemView.setOnClickListener {
                clickListener.onItemClicked(category, itemView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CategoryItemBinding>(view, R.layout.category_item, parent, false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = categories[position]
        holder.bind(category, itemClickListener)
    }

    override fun getItemCount() = categories.size
}