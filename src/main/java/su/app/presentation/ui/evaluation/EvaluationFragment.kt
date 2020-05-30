package su.app.presentation.ui.evaluation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import su.app.R
import su.app.databinding.EvaluationFragmentBinding

class EvaluationFragment : Fragment() {
    private lateinit var evaluationPagerAdapter: EvaluationPagerAdapter

    private lateinit var viewPager: ViewPager

    private lateinit var binding: EvaluationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.evaluation_fragment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        evaluationPagerAdapter = EvaluationPagerAdapter(childFragmentManager)
        viewPager = binding.evaluationViewPager
        viewPager.adapter = evaluationPagerAdapter
        binding.tabLayout.setupWithViewPager(viewPager)
    }
}