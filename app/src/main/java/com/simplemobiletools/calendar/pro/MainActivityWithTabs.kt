package com.simplemobiletools.calendar.pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simplemobiletools.calendar.pro.activities.MainActivity

import kotlinx.android.synthetic.main.activity_main_with_tabs.*

class MainActivityWithTabs : AppCompatActivity() {
    var selectedID : Int = -1
    lateinit var bottomNav: BottomNavigationView
    lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_with_tabs)
        bottomNav = findViewById(R.id.bottom_nav_bar)
        initNavBar()
    }


    private fun initNavBar() {
        fragment = Fragment()
        bottomNav.setOnNavigationItemSelectedListener { when (it.itemId) {
                R.id.menu_home -> {
                    selectedID = 0
                    fragment = HomeFramgentTemp()

                }
                R.id.menu_report -> {
                    selectedID = 1
                    fragment = ReportFragmentTemp()

                }
                R.id.menu_calendar -> {

//                    val view: View = bottomNav.findViewById(R.id.menu_home)
//                    view.performClick()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivityForResult(intent,1)

                }
            }

            supportFragmentManager.beginTransaction().replace(R.id.fl_main, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
            true
        }
        bottomNav.selectedItemId = R.id.menu_home

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            bottomNav.selectedItemId =bottomNav.menu.getItem(selectedID).itemId
        }
    }
    private fun setContent(content: String) {
        title = content

    }

}


//    private fun setUpTab() {
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(Home(), "Day Left")
//        adapter.addFragment(ReportFragment(), "Event to do left")
//        adapter.addFragment(MainFrag(), "Main")
//        view_pager_tabs.adapter = adapter
//
//
//        tabs.setupWithViewPager(view_pager_tabs)
//        tabs.getTabAt(0)!!.setText("Ngay")
//        tabs.getTabAt(1)!!.setText("Su kien")
//        tabs.getTabAt(2)!!.setText("Home")
//
//        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if (tab?.position == 2) {
//                    startActivity(intent)
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//        })
//
//    }
