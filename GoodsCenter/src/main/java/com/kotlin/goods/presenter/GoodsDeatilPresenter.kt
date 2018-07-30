package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.presenter.view.GoodsDetailView
import com.kotlin.goods.service.GoodsService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class GoodsDeatilPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {
    @Inject
    lateinit var goodService: GoodsService

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

}