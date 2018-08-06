package com.kotlin.order.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.data.repository.OrderRepository
import com.kotlin.order.service.OrderService
import rx.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class OrderServiceImpl @Inject constructor() : OrderService {


    @Inject
    lateinit var repository: OrderRepository

    override fun getOrderById(orderId: Int): Observable<Order> {
        return repository.getOrderById(orderId).convert()
    }
}