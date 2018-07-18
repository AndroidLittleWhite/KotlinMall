package com.yking.usercenter.data.repository

import com.yking.baselibrary.data.net.RetrofitFactory
import com.yking.baselibrary.data.protocol.BaseResp
import com.yking.usercenter.data.api.UserApi
import com.yking.usercenter.data.protocol.RegisterReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class UserRepository @Inject constructor(){
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile,pwd,verifyCode))
    }
}