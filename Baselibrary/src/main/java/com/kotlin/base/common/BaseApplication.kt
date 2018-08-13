package com.kotlin.base.common

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.injection.component.AppComponent
import com.kotlin.base.injection.component.DaggerAppComponent
import com.kotlin.base.injection.module.AppModule



/**
 * @author Mr_YKing on 2018/7/18.
 */
open class BaseApplication:Application() {
    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        initApplicationInjection()

        context=this

        if (isDebug()) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    private fun isDebug(): Boolean {
        try {
            val info = context.applicationInfo
            return info.flags and ApplicationInfo.FLAG_DEBUGGABLE !== 0
        } catch (e: Exception) {
            return false
        }
    }

    private fun initApplicationInjection() {
        appComponent=DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var context:Context
    }

}