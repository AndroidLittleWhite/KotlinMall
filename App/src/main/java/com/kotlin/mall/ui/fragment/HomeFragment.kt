package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.goods.ui.activity.SearchGoodsActivity
import com.kotlin.mall.R
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.HomeDiscountAdapter
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity


/**
 * Created by  on 2018/7/26.YaoKai
 */
class HomeFragment: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }

    private fun initView() {
        mSearchEt.onClick {
            startActivity<SearchGoodsActivity>()
        }
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

    private fun initDiscount() {
        val discountAdapter=HomeDiscountAdapter(context!!)
        mHomeDiscountRv.layoutManager=LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        mHomeDiscountRv.adapter=discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }
    private fun initTopic() {
        mTopicPager.adapter=TopicAdapter(context!!, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem=1
        mTopicPager.offscreenPageLimit=5

        CoverFlow.Builder()
                .with(mTopicPager)
                .pagerMargin(0f)
                .scale(0.3f)
                .spaceSize(0f)
                .rotationY(0f)
                .build()
    }
}