package com.yking.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import com.yking.baselibrary.injection.ActivityScope
import com.yking.baselibrary.injection.module.ActivityModule
import com.yking.baselibrary.injection.module.LifecycleProviderModule
import dagger.Component

/**
 * @author Mr_YKing on 2018/7/18.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class
        ,LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity(): Activity

    fun context(): Context

    fun lifecycleProvider():LifecycleProvider<*>
}