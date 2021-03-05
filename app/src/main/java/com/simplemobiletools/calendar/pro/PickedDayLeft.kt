package com.simplemobiletools.calendar.pro

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.simplemobiletools.calendar.pro.adapter2.PickedDateAdapter
import com.simplemobiletools.calendar.pro.helpers.MONTH
import com.simplemobiletools.calendar.pro.model.PickDateModel
import kotlinx.android.synthetic.main.activity_picked_day_left.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.joda.time.Months
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList


class PickedDayLeft : AppCompatActivity() {
    lateinit var saveInputDate: SaveInputDate
    @SuppressLint("WeekBasedYear")
    var smf = SimpleDateFormat("dd MMM, yyyy", Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picked_day_left)
        saveInputDate = SaveInputDate(this)
        val c = Calendar.getInstance()
        val dateBegin = smf.format(c.time)
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
    private fun splitDateToTv(text:String){
        var stringList = text.split(" ")
        tv_picked_day.text = stringList[0]
        tv_picked_month.text = stringList[1].substring(0,stringList[1].length-1)
        tv_picked_year.text = stringList[2]
    }

    private fun pickedDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, years, monthOfYear, dayOfMonth ->
            val selectDate = Calendar.getInstance()
            selectDate.set(Calendar.YEAR, years)
            selectDate.set(Calendar.MONTH, monthOfYear)
            selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val date = smf.format(selectDate.time)
            splitDateToTv(date)
            saveInputDate = SaveInputDate(this)
            saveInputDate.setDatePicked(date)
        }, year, month, day)
        dpd.show()
    }


}
