package com.yking.usercenter.injection.component

import android.support.v4.app.ActivityCompat
import com.yking.baselibrary.injection.PerComponentScope
import com.yking.baselibrary.injection.component.ActivityComponent
import com.yking.usercenter.injection.module.UserModule
import com.yking.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * Created by  on 2018/7/18.YaoKai
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponet {
    fun inject(activity:RegisterActivity)
}