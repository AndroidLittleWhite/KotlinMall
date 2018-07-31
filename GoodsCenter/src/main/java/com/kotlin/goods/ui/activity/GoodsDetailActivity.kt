package com.kotlin.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.goods.R
import com.kotlin.goods.ui.adapter.GoodsDetailVpAdapter
import com.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_goods_detail.*

/*
    商品详情 Activity
 */
class GoodsDetailActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode=TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter=GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
        }
    }
}
