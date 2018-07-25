package com.kotlin.usercenter.data.api

import com.kotlin.base.data.protocol.BaseResp
import retrofit2.http.POST
import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>

}