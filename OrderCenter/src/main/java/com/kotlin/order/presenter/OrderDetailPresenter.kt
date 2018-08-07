package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.presenter.view.OrderDetailView
import com.kotlin.order.service.OrderService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {
    @Inject
    lateinit var orderService: OrderService

    fun getOrderById(orderId: Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        orderService.getOrderById(orderId)
                .execute(object : BaseSubscribe<Order>(mView) {
                    override fun onNext(t: Order) {
                            mView.onGetOrderByIdResult(t)
                    }
                }, lifecycleProvider)
    }

}