package com.simplemobiletools.calendar.pro

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(val context: Context, val arrayList: ArrayList<EventRVModel>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventTitleTextView = itemView.findViewById<TextView>(R.id.tv_event_title)
        var eventDateLeftTextView = itemView.findViewById<TextView>(R.id.tv_day_left)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.eventTitleTextView.text = arrayList[position].eventTile
        holder.eventDateLeftTextView.text = arrayList[position].eventDate.toString()+" "+"days"
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}
