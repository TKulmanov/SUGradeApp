package su.app.presentation.ui.evaluation.attestation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import su.app.R
import su.app.ViewModelFactory
import su.app.databinding.AttestationFragmentBinding
import su.app.presentation.injectViewModel
import su.app.domain.AttestationViewModel
import javax.inject.Inject


class AttestationFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private  lateinit var  attestationViewModel: AttestationViewModel

    private lateinit var binding: AttestationFragmentBinding

    private lateinit var adapter:AttestationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        attestationViewModel = injectViewModel(viewModelFactory)
        binding = DataBindingUtil.inflate(inflater,R.layout.attestation_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeData()
        drawRecyclerView()
    }

    private fun drawRecyclerView() {
        adapter = AttestationAdapter(listOf())
        binding.attestationRecyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = DividerItemDecoration(this.context , DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(this.context!!,R.drawable.divider)!!)
        binding.attestationRecyclerView.addItemDecoration(itemDecoration)
        binding.attestationRecyclerView.adapter = adapter
    }


    private fun observeData() = GlobalScope.launch(Dispatchers.Main) {
        val attestation = attestationViewModel.attestation.await()

        attestation.observe(this@AttestationFragment, Observer { attestationList ->
            adapter.attestations = attestationList
            adapter.notifyDataSetChanged()
        })

    }
}
