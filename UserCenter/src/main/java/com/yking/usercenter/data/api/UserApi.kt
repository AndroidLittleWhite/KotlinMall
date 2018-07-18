package com.yking.usercenter.data.api

import com.yking.baselibrary.data.protocol.BaseResp
import com.yking.usercenter.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResp<String>>
}