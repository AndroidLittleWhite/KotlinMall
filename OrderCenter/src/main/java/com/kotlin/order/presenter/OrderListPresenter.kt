package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.presenter.view.OrderListView
import com.kotlin.order.service.OrderService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {
    @Inject
    lateinit var orderService: OrderService

    fun getOrderList(orderStatus: Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        orderService.getOrderList(orderStatus)
                .execute(object : BaseSubscribe<MutableList<Order>?>(mView) {
                    override fun onNext(t: MutableList<Order>?) {
                            mView.onGetOrderListResult(t)
                    }
                }, lifecycleProvider)
    }
    fun confirmOrder(orderId: Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        orderService.confirmOrder(orderId)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                            mView.onConfirmOrderResult(t)
                    }
                }, lifecycleProvider)
    }
    fun cancelOrder(orderId: Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        orderService.cancelOrder(orderId)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                            mView.onCancelOrderResult(t)
                    }
                }, lifecycleProvider)
    }

}