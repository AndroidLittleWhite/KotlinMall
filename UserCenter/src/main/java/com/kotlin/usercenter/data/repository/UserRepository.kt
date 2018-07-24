package com.kotlin.usercenter.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.usercenter.data.api.UserApi
import com.kotlin.usercenter.data.protocol.ForgetReq
import com.kotlin.usercenter.data.protocol.LoginReq
import com.kotlin.usercenter.data.protocol.RegisterReq
import com.kotlin.usercenter.data.protocol.UserInfo
import rx.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class UserRepository @Inject constructor(){
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile,pwd,verifyCode))
    }
    fun login(mobile:String,pwd:String,pushID:String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(mobile,pwd,pushID))
    }
    fun forgetPwd(mobile:String,verifyCode:String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).forgetPwd(ForgetReq(mobile,verifyCode))
    }
}