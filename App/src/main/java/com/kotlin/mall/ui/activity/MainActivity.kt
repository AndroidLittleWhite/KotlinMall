package com.kotlin.mall.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.common.AppManager
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.ui.fragment.CartFragment
import com.kotlin.goods.ui.fragment.CategoryFragment
import com.kotlin.mall.R
import com.kotlin.mall.ui.fragment.HomeFragment
import com.kotlin.mall.ui.fragment.MeFragment
import com.kotlin.message.ui.fragment.MessageFragment
import com.kotlin.provider.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mStack=Stack<Fragment>()
    private val mHomeFragment:HomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment:CategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment:CartFragment by lazy { CartFragment() }
    private val mMsgFragment:MessageFragment by lazy { MessageFragment() }
    private val mMeFragment:MeFragment by lazy { MeFragment() }

    private var firstPress:Long =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkMsgBadge(false)

        initView()
        initFragment()
        initBottomNavBar()
        changeFragment(0)
        initObserve()
    }

    private fun initView(){
        loadCartSize()
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
    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    t: UpdateCartSizeEvent ->
                    run {
                        loadCartSize()
                    }
                }
                .registerInBus(this)
        Bus.observe<MessageBadgeEvent>()
                .subscribe {
                    t: MessageBadgeEvent ->
                    run {
                        mBottomNavBar.checkMsgBadge(t.isVisible)
                    }
                }
                .registerInBus(this)
    }

    private fun loadCartSize() {
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    override fun onBackPressed() {
        val currentTime=System.currentTimeMillis()
        if (currentTime - firstPress > 2000) {
            toast("再按一次退出程序")
            firstPress=currentTime
        }else{
            AppManager.instance.exitApp(this)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
