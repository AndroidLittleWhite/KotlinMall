package com.yking.usercenter.injection.module

import com.yking.usercenter.service.UserService
import com.yking.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/7/18.YaoKai
 */
@Module
class UserModule {
    @Provides
    fun providesUserService(service: UserServiceImpl):UserService{
       return service
    }
}