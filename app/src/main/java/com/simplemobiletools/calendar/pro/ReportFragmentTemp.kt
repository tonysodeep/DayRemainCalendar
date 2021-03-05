package com.simplemobiletools.calendar.pro

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplemobiletools.calendar.pro.adapter2.PickedDateAdapter
import com.simplemobiletools.calendar.pro.iterfaces.ItemClickEvent
import com.simplemobiletools.calendar.pro.model.PickDateModel
import kotlinx.android.synthetic.main.fragment_report_temp.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class ReportFragmentTemp : Fragment(),ItemClickEvent {
    lateinit var rootView: View
    lateinit var reportAdapter: PickedDateAdapter
    @RequiresApi(Build.VERSION_CODES.O)
    var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
    lateinit var arrayList: ArrayList<PickDateModel>
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_report_temp, container, false)
        rootView.report_rv.layoutManager = LinearLayoutManager(activity as MainActivityWithTabs)
        createDataBase()
        reportAdapter = PickedDateAdapter(activity as MainActivityWithTabs,arrayList,addDateLeft(arrayList))
        reportAdapter.setClickEvent(this)
        rootView.report_rv.adapter = reportAdapter
        return rootView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createDataBase() {
        arrayList = ArrayList()
        val calendaDB = CalendaDB(activity as MainActivityWithTabs)
        arrayList.addAll(calendaDB.readEventData())
        addDateLeft(calendaDB.readEventData())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addDateLeft(arrayList: ArrayList<PickDateModel>) :ArrayList<Long>{
        var arrayListDayLeft = ArrayList<Long>()
        for (i in 0 until arrayList.size) {
            var dateString = arrayList[i].dateAndMonth +","+ arrayList[i].years
            val parseDate = LocalDate.parse(dateString, formatter)
            val dateLeft = ChronoUnit.DAYS.between(LocalDate.now(), parseDate)
            arrayListDayLeft.add(dateLeft)
        }
        return arrayListDayLeft
    }

    override fun itemClick() {
        Toast.makeText(activity as MainActivityWithTabs,"click bait",Toast.LENGTH_LONG).show()
    }
}
