package com.simplemobiletools.calendar.pro.adapter2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplemobiletools.calendar.pro.R
import com.simplemobiletools.calendar.pro.iterfaces.ItemClickEvent
import com.simplemobiletools.calendar.pro.model.PickDateModel
import kotlinx.android.synthetic.main.picked_date_item.view.*

class PickedDateAdapter(val context: Context, val list: ArrayList<PickDateModel>, private val arrayList: ArrayList<Long>)
    : RecyclerView.Adapter<PickedDateAdapter.PickedDateViewHolder>() {

    lateinit var itemClickEvent: ItemClickEvent

    class PickedDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(model: PickDateModel,date: Long) {
            itemView.tv_event_name.text = model.eventTitle
            itemView.tv_event_decription.text = model.eventDescription
            itemView.tv_date_month.text = model.dateAndMonth
            itemView.tv_year.text = model.years
            itemView.tv_date_left.text = date.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickedDateViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.picked_date_item,parent,false)
        return PickedDateViewHolder(v)
    }

    override fun onBindViewHolder(holder: PickedDateViewHolder, position: Int) {
        holder.bindItems(list[position],arrayList[position])
        holder.itemView.ic_arrow.setOnClickListener {
            itemClickEvent.itemClick()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setClickEvent(clickEvent: ItemClickEvent){
        this.itemClickEvent = clickEvent
    }

}
