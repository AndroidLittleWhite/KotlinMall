package com.kotlin.order.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

/**
 * @author Mr_YKing on 2018/7/18.
 */
interface OrderConfirmView : BaseView {
    fun onGetOrderByIdResult(result: Order)
}