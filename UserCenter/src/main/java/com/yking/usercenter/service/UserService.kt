package com.yking.usercenter.service

import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface UserService {
    fun register( mobile:String,pwd:String,verifyCode:String):Observable<Boolean>
}