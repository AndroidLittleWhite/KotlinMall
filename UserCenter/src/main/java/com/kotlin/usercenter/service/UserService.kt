package com.kotlin.usercenter.service

import com.kotlin.usercenter.data.protocol.UserInfo
import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface UserService {
    fun register( mobile:String,pwd:String,verifyCode:String):Observable<Boolean>
    fun login( mobile:String,pwd:String,pushId:String):Observable<UserInfo>
}