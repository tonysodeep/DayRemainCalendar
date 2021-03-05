package com.simplemobiletools.calendar.pro

class EventModel(title: String, date: String, eventToDoLeft: Long) {
    var id : Int = 0
    var eventDate : String = date
    var eventTitle : String = title
    var eventToDoLeft : Long = eventToDoLeft
}
