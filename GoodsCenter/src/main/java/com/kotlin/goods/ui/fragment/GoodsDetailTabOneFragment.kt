package com.kotlin.goods.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.event.GoodsDetailImageEvent
import com.kotlin.goods.injection.component.DaggerGoodsComponet
import com.kotlin.goods.injection.module.GoodsModule
import com.kotlin.goods.presenter.GoodsDeatilPresenter
import com.kotlin.goods.presenter.view.GoodsDetailView
import com.kotlin.goods.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/*
    商品详情Tab One
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDeatilPresenter>(),GoodsDetailView{

    private lateinit var mSkuPop:GoodsSkuPopView
    override fun injectComponent() {
        DaggerGoodsComponet.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initView()
        loadData()
    }

    private fun initBanner() {
        //        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
        //设置图片加载器
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        //设置banner动画效果
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        //设置标题集合（当banner样式有显示title时）
//        mHomeBanner.setBannerTitles(listOf("","","",""))
        //设置自动轮播，默认为true
        mGoodsDetailBanner.isAutoPlay(true)
        //设置轮播时间
        mGoodsDetailBanner.setDelayTime(1500)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)
    }

    private fun initView() {
        mSkuPop= GoodsSkuPopView(activity!!)

        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as BaseActivity).contentView,Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,0,0)
        }

    }

    private fun loadData() {
        mPresenter.getGoodsDEtail(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID,-1))
    }

    override fun onGetGoodsDetailResult(goods: Goods) {
        mGoodsDetailBanner.setImages(goods.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text=goods.goodsDesc
        mGoodsPriceTv.text=YuanFenConverter.changeF2YWithUnit(goods.goodsDefaultPrice)

        mSkuSelectedTv.text=goods.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(goods.goodsDetailOne,goods.goodsDetailTwo))

        loadSkuData(goods)
    }

    private fun loadSkuData(goods: Goods) {
        mSkuPop.setGoodsCode(goods.goodsCode)
        mSkuPop.setGoodsIcon(goods.goodsDefaultIcon)
        mSkuPop.setGoodsPrice(goods.goodsDefaultPrice)
        mSkuPop.setSkuData(goods.goodsSku)
    }
}
