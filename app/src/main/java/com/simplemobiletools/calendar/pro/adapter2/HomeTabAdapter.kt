package com.simplemobiletools.calendar.pro.adapter2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplemobiletools.calendar.pro.R
import com.simplemobiletools.calendar.pro.model.HomeTabModelRv1
import kotlinx.android.synthetic.main.home_tab_rv_item_1.view.*

class HomeTabAdapter(val context: Context, val list: ArrayList<HomeTabModelRv1>) :
        RecyclerView.Adapter<HomeTabAdapter.HomeTabViewHolder>() {
    class HomeTabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun blindItems(modelRv1: HomeTabModelRv1){
            itemView.tv_topic.text = modelRv1.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTabViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_tab_rv_item_1,parent,false)
        return HomeTabViewHolder(v)
    }

    override fun onBindViewHolder(holder: HomeTabViewHolder, position: Int) {
        holder.blindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
