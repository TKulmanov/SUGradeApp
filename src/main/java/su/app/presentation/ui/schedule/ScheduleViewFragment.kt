package su.app.presentation.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import su.app.R
import su.app.databinding.ScheduleViewFragmentBinding

class ScheduleViewFragment : Fragment() {

    private lateinit var schedulePagerAdapter: SchedulePagerAdapter

    private lateinit var viewPager: NonSwipeableViewPager

    private lateinit var binding: ScheduleViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.schedule_view_fragment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        schedulePagerAdapter = SchedulePagerAdapter(childFragmentManager)
        viewPager = binding.scheduleViewPager
        viewPager.adapter = schedulePagerAdapter

        binding.tabLayout.setupWithViewPager(viewPager)
    }


}