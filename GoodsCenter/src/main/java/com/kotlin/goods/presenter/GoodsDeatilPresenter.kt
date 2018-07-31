package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.presenter.view.GoodsDetailView
import com.kotlin.goods.service.CartService
import com.kotlin.goods.service.GoodsService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class GoodsDeatilPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {
    @Inject
    lateinit var goodService: GoodsService

    @Inject
    lateinit var cartService: CartService

    fun getGoodsDEtail(goodID:Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        goodService.getGoodsDetail(goodID)
                .execute(object : BaseSubscribe<Goods>(mView) {
                    override fun onNext(t: Goods) {
                            mView.onGetGoodsDetailResult(t)
                    }
                }, lifecycleProvider)
    }
    /*
            添加商品到购物车
         */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String){
        if (!checkNetWork()) {
            return
        }
        cartService.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice,goodsCount,goodsSku)
                .execute(object :BaseSubscribe<Int>(mView){
                    override fun onNext(t: Int) {
                        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE,t)
                        mView.onAddCartResult(t)
                    }
                },lifecycleProvider)
    }
}