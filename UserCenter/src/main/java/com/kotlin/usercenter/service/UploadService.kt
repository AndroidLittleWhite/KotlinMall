package com.kotlin.usercenter.service

import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface UploadService {
    fun getUploadToken(): Observable<String>
}