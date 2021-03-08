package com.simplemobiletools.calendar.pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simplemobiletools.calendar.pro.activities.MainActivity
import com.simplemobiletools.calendar.pro.adapter2.ViewPagerAdapter

import kotlinx.android.synthetic.main.activity_main_with_tabs.*

class MainActivityWithTabs : AppCompatActivity() {
    var selectedID = 0
    lateinit var bottomNav: BottomNavigationView
    lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_with_tabs)
        bottomNav = findViewById(R.id.bottom_nav_bar)

        val menuAdapter = ViewPagerAdapter(initFragment(), supportFragmentManager)

        main_view_pager?.adapter = menuAdapter

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    selectedID = 0
                    main_view_pager.currentItem = 0
                }
                R.id.menu_report -> {
                    selectedID = 1
                    main_view_pager.currentItem = 1
                }
                R.id.menu_calendar -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        main_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottomNav.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }

    override fun onResume() {
        super.onResume()
        bottomNav.selectedItemId = bottomNav.menu.getItem(selectedID).itemId
    }

    private fun initFragment(): ArrayList<Fragment> {
        return arrayListOf(
                HomeFramgentTemp(),
                ReportFragmentTemp(),
        )
    }

}


