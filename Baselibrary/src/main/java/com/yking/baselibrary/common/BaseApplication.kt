package com.yking.baselibrary.common

import android.app.Application
import android.content.Context
import com.yking.baselibrary.injection.component.AppComponent
import com.yking.baselibrary.injection.component.DaggerAppComponent
import com.yking.baselibrary.injection.module.AppModule

/**
 * @author Mr_YKing on 2018/7/18.
 */
class BaseApplication:Application() {
    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        initApplicationInjection()

        context=this
    }

    private fun initApplicationInjection() {
        appComponent=DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var context:Context
    }

}