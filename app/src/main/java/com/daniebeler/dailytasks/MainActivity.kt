package com.daniebeler.dailytasks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var btnAdd:Button
    lateinit var btnIvy: Button
    lateinit var bottomSheetDialog: BottomSheetDialogFragment

    lateinit var viewPager:ViewPager2

    lateinit var fragment1: Fragment
    lateinit var fragment2: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        viewPager = findViewById(R.id.viewpager)

        initViewPager2()

        btnAdd = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener{
            bottomSheetDialog = BottomSheetInput()
            val bundle = Bundle()
            if(viewPager.currentItem == 0){
                bundle.putString("date", "today")
            }
            else{
                bundle.putString("date", "tomorrow")
            }

            bottomSheetDialog.arguments = bundle
            bottomSheetDialog.show(supportFragmentManager, "tag")
        }

        btnIvy = findViewById(R.id.btn_ivy)
        btnIvy.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://jamesclear.com/ivy-lee")))
        }
    }

    private fun initViewPager2(){
        val adapter = StateAdapter(supportFragmentManager, lifecycle)
        fragment1 = adapter.createFragment(0)
        fragment2 = adapter.createFragment(1)

        viewPager.adapter = adapter

        val tabLayout:TabLayout = findViewById(R.id.tablayout)
        val names:ArrayList<String> = arrayListOf("Today", "Tomorrow")
        TabLayoutMediator(tabLayout, viewPager){tab,position ->
            tab.text = names[position]
        }.attach()
    }

    fun refresh(date:String){
        if(date == "today"){
            (fragment1 as TodayFragment).refreshList()
        }
        else{
            (fragment2 as TomorrowFragment).refreshList()
        }
    }
}