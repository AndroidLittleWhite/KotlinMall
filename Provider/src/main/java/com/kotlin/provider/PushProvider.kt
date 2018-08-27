package com.kotlin.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by  on 2018/8/14.YaoKai
 */
interface PushProvider:IProvider {
    fun getPushId():String
}