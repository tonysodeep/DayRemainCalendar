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
        var saveInputDate = SaveInputDate(this)
        val bitmap = BlurImage.with(applicationContext).load(R.drawable.darkbg).intensity(3F).Async(true).imageBlur
        image_background.setImageBitmap(bitmap)
        bt_toMain.setOnClickListener {
//            if (saveInputDate.getDateLeft() == 0.toLong()) {
//                val intent = Intent(this, PickedDayLeft::class.java)
//                startActivity(intent)
//            }
//            val intent = Intent(this, MainActivityWithTabs::class.java)
            val intent = Intent(this,PickedDayLeft::class.java)
            startActivity(intent)
        }
    }
}
