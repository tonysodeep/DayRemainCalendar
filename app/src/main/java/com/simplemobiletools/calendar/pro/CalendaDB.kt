package com.simplemobiletools.calendar.pro

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.simplemobiletools.calendar.pro.model.PickDateModel
import java.sql.SQLClientInfoException

val DATABASE_NAME = "Calenda_db"
val TABLE_NAME = "Event_Data"
val COL_EVENT_ID = "Id"
val COL_EVENT_DATE_MONTH = "Date_and_Month"
val COL_EVENT_YEAR = "Year"
val COL_EVENT_DESCRIPTION = "Event_Description"
val COL_EVENT_NAME = "Title"
class CalendaDB(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableEvent = "CREATE TABLE $TABLE_NAME ( $COL_EVENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_EVENT_DATE_MONTH TEXT, $COL_EVENT_YEAR TEXT, $COL_EVENT_NAME TEXT, $COL_EVENT_DESCRIPTION TEXT);"
       // val createTableDateEventLeft = "CREATE TABLE Day_left ( Id INTEGER PRIMARY KEY AUTOINCREMENT, Date_left INTEGER, Id_event INTEGER ,FOREIGN KEY(Id_event) REFERENCES ${TABLE_NAME}( ${COL_EVENT_ID}) );"
        db?.execSQL(createTableEvent)
       // db?.execSQL(createTableDateEventLeft)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun insertEventData(event:PickDateModel){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.apply {
            put(COL_EVENT_NAME,event.eventTitle)
            put(COL_EVENT_DATE_MONTH,event.dateAndMonth)
            put(COL_EVENT_YEAR,event.years)
            put(COL_EVENT_DESCRIPTION,event.eventDescription)

        }
        val result = db.insert(TABLE_NAME,null,cv)
        if (result == (-1).toLong()){
            Log.d("AAA", "Errors")
        }
    }

    fun readEventData():ArrayList<PickDateModel>{
        var list = ArrayList<PickDateModel>()
        val db = this.readableDatabase
        val query = "SELECT $TABLE_NAME.$COL_EVENT_DATE_MONTH, $TABLE_NAME.$COL_EVENT_YEAR, $TABLE_NAME.$COL_EVENT_NAME, $TABLE_NAME.$COL_EVENT_DESCRIPTION FROM $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var eventDateMonth = result.getString(result.getColumnIndex(COL_EVENT_DATE_MONTH))
                var eventYear = result.getString(result.getColumnIndex(COL_EVENT_YEAR))
                var eventTitle = result.getString(result.getColumnIndex(COL_EVENT_NAME))
                var eventDescription = result.getString(result.getColumnIndex(COL_EVENT_DESCRIPTION))
                var eventRVModel = PickDateModel(eventDateMonth,eventYear,eventTitle,eventDescription)
                list.add(eventRVModel)
            }while (result.moveToNext())
        }
        result.close()
        return list
    }
    fun readLastEventData():ArrayList<PickDateModel>{
        var list = ArrayList<PickDateModel>()
        val db = this.readableDatabase
        val query = "SELECT $TABLE_NAME.$COL_EVENT_DATE_MONTH, $TABLE_NAME.$COL_EVENT_YEAR, $TABLE_NAME.$COL_EVENT_NAME, $TABLE_NAME.$COL_EVENT_DESCRIPTION FROM $TABLE_NAME ORDER BY ID DESC LIMIT 1"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var eventDateMonth = result.getString(result.getColumnIndex(COL_EVENT_DATE_MONTH))
                var eventYear = result.getString(result.getColumnIndex(COL_EVENT_YEAR))
                var eventTitle = result.getString(result.getColumnIndex(COL_EVENT_NAME))
                var eventDescription = result.getString(result.getColumnIndex(COL_EVENT_DESCRIPTION))
                var eventRVModel = PickDateModel(eventDateMonth,eventYear,eventTitle,eventDescription)
                list.add(eventRVModel)
            }while (result.moveToNext())
        }
        result.close()
        return list
    }
}
