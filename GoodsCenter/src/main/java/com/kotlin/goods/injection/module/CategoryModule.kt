package com.kotlin.goods.injection.module

import com.kotlin.goods.service.CategroyService
import com.kotlin.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/7/18.YaoKai
 */
@Module
class CategoryModule {
    @Provides
    fun providesCategoryService(service: CategoryServiceImpl): CategroyService {
       return service
    }
}