package com.kotlin.usercenter.injection.module

import com.kotlin.usercenter.service.UploadService
import com.kotlin.usercenter.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/7/18.YaoKai
 */
@Module
class UploadModule {
    @Provides
    fun providesUserService(service: UploadServiceImpl): UploadService {
       return service
    }
}