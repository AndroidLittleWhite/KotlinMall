package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.presenter.view.GoodsListView
import com.kotlin.goods.service.GoodsService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {
    @Inject
    lateinit var goodService: GoodsService

    fun getGoodsList(categoryId:Int,pageNum:Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        goodService.getGoodsList(categoryId,pageNum)
                .execute(object : BaseSubscribe<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                            mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)
    }
    fun getGoodsListByKeyword(keyWord:String,pageNum:Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        goodService.getGoodsListByKeyword(keyWord,pageNum)
                .execute(object : BaseSubscribe<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                            mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)
    }
}