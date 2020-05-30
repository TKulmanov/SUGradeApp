package su.app.presentation.ui.evaluation.attestation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import su.app.R
import su.app.databinding.AttestationItemsBinding
import su.app.repository.model.evaluation.attestation.Attestation


class AttestationAdapter(var attestations: List<Attestation>): RecyclerView.Adapter<AttestationAdapter.AttestationHolder>(){

    class AttestationHolder(private val binding: AttestationItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(attestation: Attestation){
            binding.attestation = attestation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttestationHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<AttestationItemsBinding>(view, R.layout.attestation_items,parent,false)
        return AttestationHolder(binding)
    }

    override fun onBindViewHolder(holder: AttestationHolder, position: Int) {
        holder.bind(attestations[position])
    }

    fun refresh(attes: List<Attestation>){
        this.attestations= attes
        notifyDataSetChanged()
    }

    override fun getItemCount() = attestations.size
}