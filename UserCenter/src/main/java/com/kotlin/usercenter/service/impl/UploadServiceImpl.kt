package com.kotlin.usercenter.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.usercenter.data.repository.UploadRepository
import com.kotlin.usercenter.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class UploadServiceImpl @Inject constructor(): UploadService {
    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }

}