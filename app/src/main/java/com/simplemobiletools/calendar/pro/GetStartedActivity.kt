package com.simplemobiletools.calendar.pro

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.renderscript.RenderScript
import com.jackandphantom.blurimage.BlurImage
import com.simplemobiletools.calendar.pro.activities.MainActivity
import kotlinx.android.synthetic.main.activity_get_started.*

class GetStartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        val bitmap = BlurImage.with(applicationContext).load(R.drawable.darkbg).intensity(3F).Async(true).imageBlur
        image_background.setImageBitmap(bitmap)
        bt_toMain.setOnClickListener {
            checkInputDay()
        }
    }
    private fun checkInputDay(){
        val saveInputDate = SaveInputDate(this)
        if(saveInputDate.getDatePicked() == " "){
            val intent = Intent(this,PickedDayLeft::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this,MainActivityWithTabs::class.java)
            startActivity(intent)
        }
    }
}
