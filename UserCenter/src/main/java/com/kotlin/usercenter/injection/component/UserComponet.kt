package com.kotlin.usercenter.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.usercenter.injection.module.UploadModule
import com.kotlin.usercenter.injection.module.UserModule
import com.kotlin.usercenter.ui.activity.*
import dagger.Component

/**
 * Created by  on 2018/7/18.YaoKai
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class,UploadModule::class))
interface UserComponet {
    fun inject(activity:RegisterActivity)
    fun inject(activity:LoginActivity)
    fun inject(activity:ForgetPwdActivity)
    fun inject(activity:ResetPwdActivity)
    fun inject(activity:UserInfoActivity)
}