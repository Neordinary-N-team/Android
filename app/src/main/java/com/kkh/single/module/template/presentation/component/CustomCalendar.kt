package com.kkh.single.module.template.presentation.component

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.children
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.kkh.single.module.template.R
import com.kkh.single.module.template.databinding.CalendarDayLayoutBinding
import com.kkh.single.module.template.databinding.ViewCustomCalendarBinding
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

/**
 * 선택한 날을 "yyyy년 MM월" 형태로 리턴하는 달력
 */
class CustomCalendar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val TAG = this::class.simpleName
    private val binding: ViewCustomCalendarBinding =
        ViewCustomCalendarBinding.inflate(LayoutInflater.from(context), this, true)

    private var selectedDate: LocalDate? = null
    private var currentMonth = YearMonth.now()

    // 날짜 선택 콜백
    private var onDateSelectedListener: ((String) -> Unit)? = null

    fun setOnDateSelectedListener(listener: (String) -> Unit) {
        onDateSelectedListener = listener
    }

    // 선택된 날짜를 "yyyy년 MM월" 형태로 반환
    fun getSelectedDateFormatted(): String {
        return selectedDate?.let {
            "${it.year}년 ${it.monthValue}월"
        } ?: "${currentMonth.year}년 ${currentMonth.monthValue}월"
    }

    init {
        initCalendar()
    }

    private fun initCalendar() {
        with(binding) {
            // 현재 연월 표시
            updateYearMonthText()

            // compose의 값을 가져올 수 없어 themes.xml에 정의한 값 대신 사용
            tvYearMonth.setTextAppearance(R.style.Subtitle1SemiBold)

            // 이전 달 버튼(<) 클릭 리스너
            btnPrevMonth.setOnClickListener {
                currentMonth = currentMonth.minusMonths(1)
                mcCustom.smoothScrollToMonth(currentMonth)
                updateYearMonthText()
            }

            // 다음 달 버튼(>) 클릭 리스너
            btnNextMonth.setOnClickListener {
                currentMonth = currentMonth.plusMonths(1)
                mcCustom.smoothScrollToMonth(currentMonth)
                updateYearMonthText()
            }

            // 선택한 월에 해당하는 날짜 표시
            mcCustom.dayBinder = object : MonthDayBinder<DayViewContainer> {
                override fun create(view: View): DayViewContainer = DayViewContainer(view)

                override fun bind(container: DayViewContainer, data: CalendarDay) {
                    container.textView.text = data.date.dayOfMonth.toString()
                }
            }

            // 오늘 날짜 이전, 이후 연월은 100개월 전까지 표시
            val startMonth = currentMonth.minusMonths(100)
            val endMonth = currentMonth.plusMonths(100)

            val daysOfWeek = daysOfWeek()

            mcCustom.setup(startMonth, endMonth, daysOfWeek.first())
            mcCustom.scrollToMonth(currentMonth)
            mcCustom.monthScrollListener = { month ->
                currentMonth = month.yearMonth
                updateYearMonthText()
            }

            // 일~토 텍스트가 표시되는 뷰
            mcCustom.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    if (container.titlesContainer.tag == null) {
                        container.titlesContainer.tag = data.yearMonth
                        container.titlesContainer.children.map { it as TextView }
                            .forEachIndexed { index, textView ->
                                val dayOfWeek = daysOfWeek[index]
                                val title = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                                textView.text = title
                            }
                    }
                }
            }

            // 날짜가 표시되는 뷰
            mcCustom.dayBinder = object : MonthDayBinder<DayViewContainer> {
                override fun create(view: View) = DayViewContainer(view)
                override fun bind(container: DayViewContainer, data: CalendarDay) {
                    container.textView.text = data.date.dayOfMonth.toString()
                    
                    // 오늘 날짜 가져오기
                    val today = LocalDate.now()
                    val isFutureDate = data.date.isAfter(today)
                    
                    // 텍스트 색상 설정
                    when {
                        isFutureDate -> {
                            // 미래 날짜는 항상 회색으로 표시
                            container.textView.setTextColor(Color.GRAY)
                        }
                        data.position == DayPosition.MonthDate -> {
                            // 현재 월에 속한 과거 또는 오늘 날짜는 검정색
                            container.textView.setTextColor(Color.BLACK)
                        }
                        else -> {
                            // 이전/다음 달의 날짜는 회색
                            container.textView.setTextColor(Color.GRAY)
                        }
                    }
                    
                    container.textView.background = null
                    
                    // 선택된 날짜 스타일 적용 (미래 날짜가 아닌 경우만)
                    if (selectedDate == data.date && !isFutureDate) {
                        // 원형 배경 설정
                        container.textView.background = GradientDrawable().apply {
                            shape = GradientDrawable.OVAL
                            setColor(NeodinaryColors.Green.Green400.toArgb())
                        }
                        
                        // 선택된 날짜는 흰색 텍스트
                        container.textView.setTextColor(Color.WHITE)
                        container.textView.gravity = Gravity.CENTER
                    }
                    
                    // 날짜 클릭 리스너 - 미래 날짜는 선택 불가
                    container.textView.setOnClickListener {
                        // 현재 월에 속한 과거 또는 오늘 날짜만 선택 가능
                        if (data.position == DayPosition.MonthDate && !isFutureDate) {
                            if (selectedDate != data.date) {
                                val oldDate = selectedDate
                                selectedDate = data.date
                                
                                // 이전 선택된 날짜 갱신
                                oldDate?.let { date ->
                                    mcCustom.notifyDateChanged(date)
                                }
                                
                                // 새로 선택된 날짜 갱신 후 콜백에 전달
                                mcCustom.notifyDateChanged(data.date)
                                onDateSelectedListener?.invoke(getSelectedDateFormatted())
                            }
                        }
                    }
                }
            }

        }
    }

    private fun updateYearMonthText() {
        binding.tvYearMonth.text = "${currentMonth.year}년 ${currentMonth.monthValue}월"
    }

}

class DayViewContainer(view: View) : ViewContainer(view) {
     val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
}

class MonthViewContainer(view: View) : ViewContainer(view) {
    val titlesContainer = view as ViewGroup
}