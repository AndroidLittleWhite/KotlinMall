package com.kotlin.goods.injection.module

import com.kotlin.goods.service.CartService
import com.kotlin.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/7/18.YaoKai
 */
@Module
class CartModule {
    @Provides
    fun providesCartService(service: CartServiceImpl): CartService {
       return service
    }
}