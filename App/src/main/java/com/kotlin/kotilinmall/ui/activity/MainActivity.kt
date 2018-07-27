package com.kotlin.kotilinmall.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.kotlin.kotilinmall.R
import com.kotlin.kotilinmall.ui.fragment.HomeFragment
import com.kotlin.kotilinmall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mStack=Stack<Fragment>()
    private val mHomeFragment:HomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment:HomeFragment by lazy { HomeFragment() }
    private val mCartFragment:HomeFragment by lazy { HomeFragment() }
    private val mMsgFragment:HomeFragment by lazy { HomeFragment() }
    private val mMeFragment:MeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(0)
        mBottomNavBar.checkMsgBadge(false)

        initFragment()
        initBottomNavBar()
        changeFragment(0)
    }

    private fun initFragment() {
        val maneger=supportFragmentManager.beginTransaction()
        maneger.add(R.id.mContainer,mHomeFragment)
        maneger.add(R.id.mContainer,mCategoryFragment)
        maneger.add(R.id.mContainer,mCartFragment)
        maneger.add(R.id.mContainer,mMsgFragment)
        maneger.add(R.id.mContainer,mMeFragment)
        maneger.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
    }

    private fun initBottomNavBar() {
        mBottomNavBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }
            override fun onTabUnselected(position: Int) {
            }
            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
    }

    private fun changeFragment(position: Int) {
        val maneger=supportFragmentManager.beginTransaction()
        for (fragment in mStack){
            maneger.hide(fragment)
        }
        maneger.show(mStack[position])
        maneger.commit()
    }
}
