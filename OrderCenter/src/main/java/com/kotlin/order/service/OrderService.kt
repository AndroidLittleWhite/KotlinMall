package com.kotlin.order.service

import com.kotlin.order.data.protocol.Order
import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface OrderService {
    fun getOrderById(orderId: Int): Observable<Order>
}