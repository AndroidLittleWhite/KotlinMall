package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.CartGoods

/**
 * @author Mr_YKing on 2018/7/18.
 */
interface CartListView : BaseView {
    fun onGetCartListResult(mutableList: MutableList<CartGoods>?)
    fun onDeleteCartListResult(boolean: Boolean)
    fun onSubmitCartListResult(int: Int)
}