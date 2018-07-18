package com.yking.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.yking.baselibrary.injection.ActivityScope
import com.yking.baselibrary.injection.module.ActivityModule
import com.yking.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author Mr_YKing on 2018/7/18.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity(): Activity
}