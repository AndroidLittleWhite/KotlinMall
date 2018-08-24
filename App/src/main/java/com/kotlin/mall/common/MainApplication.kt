package com.kotlin.mall.common

import cn.jpush.android.api.JPushInterface
import com.kotlin.base.common.BaseApplication

/**
 * @author Mr_YKing on 2018/8/13.
 */
class MainApplication:BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}