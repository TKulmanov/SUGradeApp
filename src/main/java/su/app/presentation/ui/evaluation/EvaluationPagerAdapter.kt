package su.app.presentation.ui.evaluation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import su.app.presentation.ui.evaluation.attestation.AttestationFragment
import su.app.presentation.ui.evaluation.transcript.TranscriptFragment

class EvaluationPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager){
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        when(position){
            0-> return AttestationFragment()
            1-> return TranscriptFragment()
        }
        return AttestationFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            0-> return "Аттестация"
            1-> return "Транскрипт"
        }
        return "Aттестация"
    }
}