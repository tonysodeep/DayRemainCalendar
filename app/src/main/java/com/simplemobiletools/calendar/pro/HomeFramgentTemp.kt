package com.simplemobiletools.calendar.pro

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_home_framgent_temp.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


class HomeFramgentTemp : Fragment() {


    lateinit var rootView: View

    @RequiresApi(Build.VERSION_CODES.O)
    var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var saveInputDate = SaveInputDate(activity as MainActivityWithTabs)
        rootView = inflater.inflate(R.layout.fragment_home_framgent_temp, container, false)
        setDate(saveInputDate.getDatePicked())
        return rootView
    }

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate(dateText: String?) {
        val parseDate = LocalDate.parse(dateText, formatter)
        val dateLeft = ChronoUnit.DAYS.between(LocalDate.now(), parseDate)
        val monthLeft = ChronoUnit.MONTHS.between(LocalDate.now(), parseDate)
        val yearLeft = ChronoUnit.YEARS.between(LocalDate.now(), parseDate)
        rootView.day_num.text = dateLeft.toString()
        rootView.month_num.text = monthLeft.toString()
        rootView.year_num.text = yearLeft.toString()
    }

}
