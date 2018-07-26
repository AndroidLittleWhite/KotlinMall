package com.kotlin.kotilinmall.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.kotilinmall.R
import com.kotlin.kotilinmall.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(0)
        mBottomNavBar.checkMsgBadge(false)

        val maneger=supportFragmentManager.beginTransaction()
        maneger.add(R.id.mContainer,HomeFragment())
        maneger.commit()

    }
}
