package com.kkh.single.module.template.presentation.component

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.children
import coil3.BitmapImage
import coil3.DrawableImage
import coil3.Image
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.kkh.single.module.template.R
import com.kkh.single.module.template.databinding.CalendarDayLayoutBinding
import com.kkh.single.module.template.databinding.ViewCustomCalendarWithPhotoBinding
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class CustomCalendarWithPhoto @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val TAG = this::class.simpleName
    private val binding: ViewCustomCalendarWithPhotoBinding =
        ViewCustomCalendarWithPhotoBinding.inflate(LayoutInflater.from(context), this, true)

    private var selectedDate: LocalDate? = null
    private var currentMonth = YearMonth.now()
    private val datesWithPhoto = mutableSetOf<LocalDate>()

    // 날짜 선택 콜백
    private var onDateSelectedListener: ((String) -> Unit)? = null

    fun setOnDateSelectedListener(listener: (String) -> Unit) {
        onDateSelectedListener = listener
    }

    // 선택된 날짜를 "yyyy년 MM월" 형태로 반환
    fun getSelectedDateFormatted(): String {
        return selectedDate?.let {
            "${it.year}.${it.monthValue}.${it.dayOfMonth}"
        } ?: "${currentMonth.year}.${currentMonth.monthValue}"
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
            mcCustom.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainerWithPhoto> {
                override fun create(view: View) = MonthViewContainerWithPhoto(view)
                override fun bind(container: MonthViewContainerWithPhoto, data: CalendarMonth) {
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
            mcCustom.dayBinder = object : MonthDayBinder<DayViewContainerWithPhoto> {
                override fun create(view: View) = DayViewContainerWithPhoto(view)
                override fun bind(container: DayViewContainerWithPhoto, data: CalendarDay) {
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
                            setColor(NeodinaryColors.Green.Green300.toArgb())
                        }

                        // 선택된 날짜는 흰색 텍스트
                        container.textView.setTextColor(Color.WHITE)
                        container.textView.gravity = Gravity.CENTER
                    } else if (!isFutureDate && selectedDate != data.date) {
                        // 이미지 URL을 가져오는 코드 (실제 앱에 맞게 수정 필요)
                        val imageUrl = getImageUrlForDate(data.date)

                        // 이미지로드 함수 호출
                        loadCircularImageWithBorder(imageUrl, container.textView)

                        // 텍스트 색상 등 다른 스타일 설정
                        container.textView.setTextColor(Color.WHITE)
                    }

                    // 날짜 상하좌우 간격 설정
                    val params = container.textView.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(dpToPx(4), dpToPx(4), dpToPx(4), dpToPx(4))
                    container.textView.layoutParams = params

                    // 날짜 클릭 리스너 - 미래 날짜는 선택 불가
                    container.textView.setOnClickListener {
                        // 현재 월에 속한 과거 또는 오늘 날짜만 선택 가능
                        Log.e(TAG, "## [달력] daysOfWeek: $daysOfWeek")
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

                private fun Image.toDrawable(resources: Resources): Drawable {
                    return when (this) {
                        is BitmapImage -> BitmapDrawable(resources, bitmap)
                        is DrawableImage -> drawable
                        else -> ColorDrawable(Color.TRANSPARENT)
                    }
                }

                private fun loadCircularImageWithBorder(imageUrl: String, view: View) {
                    val imageRequest = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .transformations(CircleCropTransformation())
                        .target { image ->
                            // Image를 Drawable로 변환
                            val imageDrawable = image.toDrawable(resources)

                            val border = GradientDrawable().apply {
                                shape = GradientDrawable.OVAL
                                setStroke(dpToPx(2), NeodinaryColors.Green.Green400.toArgb())
                                setColor(Color.TRANSPARENT)
                            }

                            // 명시적으로 Drawable 배열 생성
                            val layers = arrayOf<Drawable>(imageDrawable, border)
                            val layerDrawable = LayerDrawable(layers)
                            view.background = layerDrawable
                        }
                        .build()

                    ImageLoader(context).enqueue(imageRequest)
                }
            }

        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    private fun getImageUrlForDate(date: LocalDate): String {
        return "https://static.vecteezy.com/vite/assets/photo-masthead-375-BoK_p8LG.webp"
    }

    private fun updateYearMonthText() {
        binding.tvYearMonth.text = "${currentMonth.year}년 ${currentMonth.monthValue}월"
    }

    /**
     * 특정 날짜가 속한 주의 모든 날짜(일~토)를 반환합니다.
     * @param date 기준 날짜
     * @return 일요일부터 토요일까지의 날짜 리스트
     */
    fun getDatesOfWeek(date: LocalDate = selectedDate ?: LocalDate.now()): List<LocalDate> {
        // 해당 날짜의 요일 (1: 월요일, 7: 일요일)
        val dayOfWeek = date.dayOfWeek.value

        // 일요일(7)로 변환 (Java에서는 일요일이 1, 토요일이 7이지만 LocalDate에서는 월요일이 1, 일요일이 7)
        val sundayAdjustedValue = if (dayOfWeek == 7) 0 else dayOfWeek

        // 해당 주의 일요일 날짜 계산 (현재 날짜에서 요일값만큼 빼기)
        val sunday = date.minusDays(sundayAdjustedValue.toLong())

        // 일요일부터 토요일까지의 날짜 리스트 생성
        return (0..6).map { sunday.plusDays(it.toLong()) }
    }

    /**
     * 현재 선택된 날짜가 속한 주의 모든 날짜(일~토)를 포맷팅하여 반환합니다.
     * 형식: ["yyyy.MM.dd", "yyyy.MM.dd", ...]
     */
    fun getFormattedDatesOfWeek(): List<String> {
        return getDatesOfWeek().map { date ->
            "${date.dayOfMonth}"
        }
    }
}

class DayViewContainerWithPhoto(view: View) : ViewContainer(view) {
    val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
}

class MonthViewContainerWithPhoto(view: View) : ViewContainer(view) {
    val titlesContainer = view as ViewGroup
}