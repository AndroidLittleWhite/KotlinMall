package com.kotlin.kotilinmall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.kotilinmall.R
import com.kotlin.mall.common.HOME_BANNER_FOUR
import com.kotlin.mall.common.HOME_BANNER_ONE
import com.kotlin.mall.common.HOME_BANNER_THREE
import com.kotlin.mall.common.HOME_BANNER_TWO
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by  on 2018/7/26.YaoKai
 */
class HomeFragment: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initNews()
    }

    private fun initBanner() {
//        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
        //设置图片加载器
        mHomeBanner.setImageLoader(BannerImageLoader())
        //设置图片集合
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        //设置banner动画效果
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        //设置标题集合（当banner样式有显示title时）
//        mHomeBanner.setBannerTitles(listOf("","","",""))
        //设置自动轮播，默认为true
        mHomeBanner.isAutoPlay(true)
        //设置轮播时间
        mHomeBanner.setDelayTime(1500)
        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start()
    }
    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("新用户注册立减500","清爽夏日，一起来学kotlin吧"))
    }
}