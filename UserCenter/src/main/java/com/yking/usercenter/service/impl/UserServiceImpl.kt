package com.yking.usercenter.service.impl

import com.yking.baselibrary.ext.convertBoolean
import com.yking.usercenter.data.repository.UserRepository
import com.yking.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class UserServiceImpl @Inject constructor(): UserService {
    @Inject
    lateinit var repository:UserRepository
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile,pwd,verifyCode)
                .convertBoolean()
    }
}