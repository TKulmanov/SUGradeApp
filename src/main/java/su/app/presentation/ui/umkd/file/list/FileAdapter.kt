package su.app.presentation.ui.umkd.file.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.FileItemBinding
import su.app.repository.model.umkd.File

class FileAdapter(var files: List<File>, private val itemClickListener: FileClickListener) : RecyclerView.Adapter<FileAdapter.FileHolder>(){

    class FileHolder(val binding: FileItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(file: File,clickListener: FileClickListener) {
            binding.file = file
            itemView.setOnClickListener {
                clickListener.onItemClicked(file,itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FileItemBinding>(view, R.layout.file_item,parent,false)
        return FileHolder(binding)
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        val discipline = files[position]
        holder.bind(discipline,itemClickListener)
    }

    override fun getItemCount() = files.size
}