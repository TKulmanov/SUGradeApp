package su.app.presentation.ui.schedule

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import su.app.presentation.ui.schedule.exam.ExamFragment
import su.app.presentation.ui.schedule.schedule.ScheduleFragment

class SchedulePagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager){
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        when(position){
            0-> return ScheduleFragment()
            1-> return ExamFragment()
        }
        return ScheduleFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            0-> return "Расписание"
            1-> return "Расписание экзаменов"
        }
        return "Расписание"
    }
}