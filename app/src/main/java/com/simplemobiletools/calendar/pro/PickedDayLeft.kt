package com.simplemobiletools.calendar.pro

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_picked_day_left.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class PickedDayLeft : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    lateinit var saveInputDate: SaveInputDate
    lateinit var myCalendar : Calendar
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0

    @SuppressLint("WeekBasedYear")
    var smf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picked_day_left)
        saveInputDate = SaveInputDate(this)
        val c = Calendar.getInstance()
        val dateBegin = smf.format(c.time)
        Log.d("DateTime",dateBegin)
        splitDateToTv(dateBegin)
        saveInputDate.setDatePicked(dateBegin)

        bt_toMain.setOnClickListener {
            val intent = Intent(this, MainActivityWithTabs::class.java)
            startActivity(intent)
        }
        card_picked_date.setOnClickListener {
            pickedDate()
        }

    }

    private fun splitDateToTv(text: String) {
        var stringList = text.split(" ")
        var dateText = stringList[0]
        var timeText = stringList[1]
        Log.d("datetime", "time $timeText")
        Log.d("datetime", "full date $text")
        tv_picked_day.text = dateText.subSequence(8, 10).toString()
        tv_picked_month.text = dateText.subSequence(5, 7).toString()
        tv_picked_year.text = dateText.subSequence(0, 4).toString()

        tv_picked_hours.text = timeText.subSequence(0, 2).toString()
        tv_picked_minute.text = timeText.subSequence(3, 5).toString()
        tv_picked_second.text = timeText.subSequence(6, 8).toString()

    }

    private fun pickedDate() {

        val calendar: Calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
        val datePickerDialog =
                DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month
        myCalendar = Calendar.getInstance()
        hour = myCalendar.get(Calendar.HOUR)
        minute = myCalendar.get(Calendar.MINUTE)
        Log.d("DateTime","myMonth $myMonth ~~ month $month")
        val timePickerDialog = TimePickerDialog(this, this, hour, minute,
                android.text.format.DateFormat.is24HourFormat(this))
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        myCalendar = Calendar.getInstance()
        myCalendar.apply {
            set(Calendar.YEAR,myYear)
            set(Calendar.MONTH,myMonth)
            set(Calendar.DAY_OF_MONTH,myDay)
            set(Calendar.HOUR,myHour)
            set(Calendar.MINUTE,myMinute)
        }
        val date = smf.format(myCalendar.time)
        splitDateToTv(date)
        saveInputDate.setDatePicked(date)

    }
}
