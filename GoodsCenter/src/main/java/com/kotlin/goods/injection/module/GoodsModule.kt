package com.kotlin.goods.injection.module

import com.kotlin.goods.service.GoodsService
import com.kotlin.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/7/18.YaoKai
 */
@Module
class GoodsModule {
    @Provides
    fun providesGoodsService(service: GoodsServiceImpl): GoodsService {
       return service
    }
}