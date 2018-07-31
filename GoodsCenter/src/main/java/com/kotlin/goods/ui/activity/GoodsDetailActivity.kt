package com.kotlin.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.ui.adapter.GoodsDetailVpAdapter
import com.kotlin.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import q.rorbin.badgeview.QBadgeView

/*
    商品详情 Activity
 */
class GoodsDetailActivity:BaseActivity() {

    private lateinit var mBadgeVIew:QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initObserve()
        loadCartSize()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode=TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter=GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin{
                Bus.send(AddCartEvent())
            }
        }

        mBadgeVIew=QBadgeView(this)
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    t: UpdateCartSizeEvent ->
                    run {
                        changeCartSize()
                    }
                }
                .registerInBus(this)

    }
    private fun loadCartSize() {
        changeCartSize()
    }

    private fun changeCartSize() {
        mBadgeVIew.setBadgeGravity(Gravity.END or Gravity.TOP)
        mBadgeVIew.setGravityOffset(22f,-2f,true)
        mBadgeVIew.setBadgeTextSize(6f,true)
        mBadgeVIew.bindTarget(mEnterCartTv).badgeNumber=AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
