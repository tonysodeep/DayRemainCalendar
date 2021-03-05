package com.simplemobiletools.calendar.pro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplemobiletools.calendar.pro.*
import kotlinx.android.synthetic.main.fragment_report.view.*


class ReportFragment : Fragment() {
    lateinit var rootView: View
    lateinit var arrayListEvent: ArrayList<EventRVModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_report, container, false)

        return rootView

    }








}
