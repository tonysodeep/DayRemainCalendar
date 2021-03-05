package com.simplemobiletools.calendar.pro

import android.content.Context

class SaveInputDate(context : Context) {
    val PREFERENCE_NAME = "SharedDate"
    val PREFERENCE_DATE_PICKED = "DatePicked"
    val PREFERENCE_DATE_LEFT = "DateLeft"


    val preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
    fun getDatePicked(): String? {
        return preferences.getString(PREFERENCE_DATE_PICKED, " ")
    }
    fun setDatePicked(date:String){
        val editor = preferences.edit()
        editor.putString(PREFERENCE_DATE_PICKED,date)
        editor.apply()
    }
    fun getDateLeft():Long{
        return preferences.getLong(PREFERENCE_DATE_LEFT,0)
    }
    fun setDateLeft(date : Long){
        val editor = preferences.edit()
        editor.putLong(PREFERENCE_DATE_LEFT,date)
        editor.apply()
    }

}
