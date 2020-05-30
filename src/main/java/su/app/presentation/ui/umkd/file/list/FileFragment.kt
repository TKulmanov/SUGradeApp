package su.app.presentation.ui.umkd.file.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment

import su.app.R
import su.app.ViewModelFactory
import su.app.databinding.FileFragmentBinding
import su.app.domain.FileViewModel
import su.app.presentation.injectViewModel
import su.app.repository.model.umkd.File
import javax.inject.Inject

class FileFragment : Fragment(), FileClickListener {


    private lateinit var fileViewModel: FileViewModel

    private lateinit var binding: FileFragmentBinding

    private lateinit var adapter: FileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.file_fragment,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = arguments?.let { FileFragmentArgs.fromBundle(it) }
        val files = args?.category?.files

        drawRecyclerView(files!!)
    }

    private fun drawRecyclerView(files: List<File>){
        adapter = FileAdapter(files, this)
        binding.fileRecyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = DividerItemDecoration(this.context , DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(AppCompatResources.getDrawable(this.context!!, R.drawable.divider)!!)
        binding.fileRecyclerView.addItemDecoration(itemDecoration)
        binding.fileRecyclerView.adapter = adapter
    }


    private fun showFiles(file: File, view: View){
        val actionDetails = FileFragmentDirections?.toFile(file)
        Navigation.findNavController(view).navigate(actionDetails)
    }

    override fun onItemClicked(file: File, view: View) {
        showFiles(file,view)
    }




}
