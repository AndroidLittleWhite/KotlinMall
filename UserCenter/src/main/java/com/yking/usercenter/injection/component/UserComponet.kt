package com.yking.usercenter.injection.component

import com.yking.usercenter.injection.module.UserModule
import com.yking.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * Created by  on 2018/7/18.YaoKai
 */
@Component(modules = arrayOf(UserModule::class))
interface UserComponet {
    fun inject(activity:RegisterActivity)
}