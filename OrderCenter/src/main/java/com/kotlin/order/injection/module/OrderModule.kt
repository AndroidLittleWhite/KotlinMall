package com.kotlin.order.injection.module

import com.kotlin.order.service.OrderService
import com.kotlin.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/7/18.YaoKai
 */
@Module
class OrderModule {
    @Provides
    fun providesOrderService(service: OrderServiceImpl): OrderService {
       return service
    }
}