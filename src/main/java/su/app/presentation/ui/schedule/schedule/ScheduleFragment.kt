package su.app.presentation.ui.schedule.schedule

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.calendar_day_layout.view.*
import kotlinx.android.synthetic.main.schedule_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter
import su.app.R
import su.app.ViewModelFactory
import su.app.databinding.ScheduleFragmentBinding
import su.app.presentation.injectViewModel
import su.app.domain.ScheduleViewModel
import su.app.repository.model.schedule.Schedule
import javax.inject.Inject

class ScheduleFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private  lateinit var  scheduleViewModel: ScheduleViewModel

    private lateinit var binding: ScheduleFragmentBinding
    private  lateinit var adapter: ScheduleAdapter

    private lateinit var scheduleListLocal: List<Schedule>

    private val dateFormatter = DateTimeFormatter.ofPattern("d")
    private var currentDate = LocalDate.now()
    private var oldDate: LocalDate? = null
    private var selectedDate: LocalDate = currentDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel = injectViewModel(viewModelFactory)

        binding = DataBindingUtil.inflate(inflater,R.layout.schedule_fragment,container,false)

        val root = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initCalendar()
        observeData(selectedDate)
        drawRecyclerView()

        binding.recyclerView.setOnTouchListener(object: onSwipeTouchListener(){
            override fun onSwipeLeft() = swipeLeft()
            override fun onSwipeRight() = swipeRight()
        })
        binding.scheduleEmptyConstraint.setOnTouchListener(object: onSwipeTouchListener(){
            override fun onSwipeLeft() = swipeLeft()
            override fun onSwipeRight() = swipeRight()
        })
    }

    private fun observeData(selectedDate: LocalDate) = GlobalScope.launch(Dispatchers.Main){
        val schedule = scheduleViewModel.schedule.await()

        schedule.observe(this@ScheduleFragment, Observer { scheduleList ->
            if (scheduleList == null) return@Observer
            scheduleListLocal = scheduleList
            castSchedule(selectedDate,scheduleList)
        })

    }


    private fun castSchedule(selectedDate: LocalDate, schedule: List<Schedule>){
        var scheduleDay = ArrayList<Schedule>()
        for(item in schedule){
            if(item.lessonDay == selectedDate.dayOfWeek.toString()){
                scheduleDay.add(item)
            }
        }

        if(scheduleDay.isEmpty()){
            binding.scheduleEmptyConstraint.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            return
        }
        var scheduleList =  listOf(
            Schedule(lessonStart = "7:50",lessonEnd = "8:40"),
            Schedule(lessonStart = "8:55",lessonEnd = "9:45"),
            Schedule(lessonStart = "10:00",lessonEnd = "10:50"),
            Schedule(lessonStart = "11:05",lessonEnd = "11:55"),
            Schedule(lessonStart = "12:10",lessonEnd = "13:00"),
            Schedule(lessonStart = "13:15",lessonEnd = "14:05"),
            Schedule(lessonStart = "14:20",lessonEnd = "15:10"),
            Schedule(lessonStart = "15:25",lessonEnd = "16:15"),
            Schedule(lessonStart = "16:30",lessonEnd = "17:20"),
            Schedule(lessonStart = "17:30",lessonEnd = "18:20"),
            Schedule(lessonStart = "18:30",lessonEnd = "19:20"),
            Schedule(lessonStart = "19:30",lessonEnd = "20:20"),
            Schedule(lessonStart = "20:30",lessonEnd = "21:20"),
            Schedule(lessonStart = "21:30",lessonEnd = "22:20")
        )

        for (item in scheduleList) {
            for (item2 in scheduleDay) {
                if (item.lessonStart == item2.lessonStart) {
                    item.id = item2.id
                    item.lessonTitle = item2.lessonTitle
                    item.lessonTutor = item2.lessonTutor
                    item.lessonType = item2.lessonType
                    item.lessonBuilding = item2.lessonBuilding
                    item.lessonClass = item2.lessonClass
                }
            }
        }

        adapter.setScheduleByDay(scheduleList)
        binding.scheduleEmptyConstraint.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE

//      var visibility = 0
//        scheduleList.forEach {
//            if(it.id == 0)
//                visibility +=1
//        }
//        if(visibility == 14){
//            binding.scheduleEmptyConstraint.visibility = View.VISIBLE
//            binding.recyclerView.visibility = View.GONE
//            return
//        }else{
//            binding.scheduleEmptyConstraint.visibility = View.GONE
//            binding.recyclerView.visibility = View.VISIBLE
//            adapter.setScheduleByDay(scheduleList)

//            drawRecyclerView(scheduleList)
//        }
    }

//    private fun drawRecyclerView(schedule: List<Schedule>) {
//        println(schedule)
//        adapter = ScheduleAdapter(schedule,this.context)
//        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.setOnTouchListener(object: onSwipeTouchListener(){
//            override fun onSwipeLeft() = swipeLeft()
//            override fun onSwipeRight() = swipeRight()
//        })
//    }

    private fun drawRecyclerView() {
        adapter = ScheduleAdapter(listOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = DividerItemDecoration(this.context , DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(this.context!!,R.drawable.divider)!!)
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.recyclerView.adapter = adapter
    }

    private fun initCalendar(){
        val dm = DisplayMetrics()
        val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(dm)
        calendarView.dayWidth =dm.widthPixels / 7
        calendarView.dayHeight = (calendarView.dayWidth * 0.6).toInt()


        class DayViewContainer(view: View): ViewContainer(view)  {
            val dateView = view.calendarDateText
            lateinit var day: CalendarDay
            init{
                view.setOnClickListener {
                    if(selectedDate!=day.date){
                        oldDate = selectedDate
                        currentDate = null
                        selectedDate = day.date
                        castSchedule(selectedDate,scheduleListLocal)
                        calendarView.notifyDateChanged(selectedDate)
                        oldDate?.let{calendarView.notifyDateChanged(it)}
                    }
                }
            }

            fun bind(day: CalendarDay){
                this.day = day
                dateView.text = dateFormatter.format(day.date)
                dateView.setTextColor(resources.getColor(if(day.date == selectedDate || day.date == currentDate) R.color.textColorLight else R.color.textColor))
                if(day.date.dayOfWeek.toString() == DayOfWeek.SUNDAY.toString() && day.date != selectedDate) dateView.setTextColor(resources.getColor(R.color.red_date))

                dateView.background = if(day.date == selectedDate || day.date == currentDate) resources.getDrawable(R.drawable.calendar_bg_round) else null
            }
        }

        calendarView.dayBinder = object : DayBinder<DayViewContainer>{
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) = container.bind(day)
        }
        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(1)
        val lastMonth = currentMonth.plusMonths(1)
        val firstDayOfWeek = DayOfWeek.MONDAY
        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
        calendarView.scrollToDate(currentDate)
    }

    private fun swipeLeft(){
        oldDate = selectedDate
        selectedDate = selectedDate.plusDays(1)
        currentDate = null
        castSchedule(selectedDate,scheduleListLocal)
        calendarView.notifyDateChanged(selectedDate)
        oldDate?.let{calendarView.notifyDateChanged(it)}
        calendarView.scrollToDate(selectedDate)
    }
    private fun swipeRight(){
        oldDate = selectedDate
        selectedDate = selectedDate.minusDays(1)
        currentDate = null
        castSchedule(selectedDate,scheduleListLocal)
        calendarView.notifyDateChanged(selectedDate)
        oldDate?.let{calendarView.notifyDateChanged(it)}
        calendarView.scrollToDate(selectedDate)
    }

}
