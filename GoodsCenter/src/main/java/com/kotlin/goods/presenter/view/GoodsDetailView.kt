package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Goods

/**
 * @author Mr_YKing on 2018/7/18.
 */
interface GoodsDetailView : BaseView {
    fun onGetGoodsDetailResult(goods: Goods)
    fun onAddCartResult(result: Int)
}