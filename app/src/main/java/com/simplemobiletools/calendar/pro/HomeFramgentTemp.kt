package com.simplemobiletools.calendar.pro

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_picked_day_left.*
import kotlinx.android.synthetic.main.fragment_home_framgent_temp.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


class HomeFramgentTemp : Fragment() {


    lateinit var rootView: View
    lateinit var saveInputDate: SaveInputDate
    lateinit var countDownTimer: CountDownTimer

    @RequiresApi(Build.VERSION_CODES.O)
    var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        saveInputDate = SaveInputDate(activity as MainActivityWithTabs)
        rootView = inflater.inflate(R.layout.fragment_home_framgent_temp, container, false)
        setDate(saveInputDate.getDatePicked())
        return rootView
    }

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate(dateText: String?) {
        val parseDate = LocalDate.parse(dateText, formatter)
        Log.d("DateTime", "string to date $parseDate")
        val dateLeft = ChronoUnit.DAYS.between(LocalDate.now(), parseDate)
        val monthLeft = dateLeft % 365
        val dateStringLeft = monthLeft % 12
        val yearLeft = ChronoUnit.YEARS.between(LocalDate.now(), parseDate)
        rootView.day_num.text = dateStringLeft.toString()
        rootView.month_num.text = monthLeft.toString()
        rootView.year_num.text = yearLeft.toString()
        printDifferenceDateForHours()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun printDifferenceDateForHours() {
        val currentTime = Calendar.getInstance().time
        val endDateDay = saveInputDate.getDatePicked()
        val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val endDate = format1.parse(endDateDay)
        //milliseconds
        var different = endDate.time - currentTime.time
        countDownTimer = object : CountDownTimer(different, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60

                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli

                Log.d("Time", "$elapsedHours hs $elapsedMinutes min $elapsedSeconds sec")
                rootView.hour_num.text = elapsedHours.toString()
                rootView.minute_num.text = elapsedMinutes.toString()
                rootView.second_num.text = elapsedSeconds.toString()

            }

            override fun onFinish() {

            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }


}
