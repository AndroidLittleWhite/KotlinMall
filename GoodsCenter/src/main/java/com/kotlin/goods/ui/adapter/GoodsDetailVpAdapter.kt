package com.kotlin.goods.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kotlin.goods.ui.fragment.GoodsDetailTabOneFragment
import com.kotlin.goods.ui.fragment.GoodsDetailTabTwoFragment

/**
 * Created by  on 2018/7/30.YaoKai
 */
class GoodsDetailVpAdapter(fm:FragmentManager,context: Context):FragmentPagerAdapter(fm){
    private val title= arrayListOf<String>("商品","详情")
    override fun getItem(position: Int): Fragment {
        return if(position==0){
            GoodsDetailTabOneFragment()
        }else{
            GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int {
        return title.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}