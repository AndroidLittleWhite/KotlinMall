package com.yking.usercenter.service.impl

import com.yking.baselibrary.data.protocol.BaseResp
import com.yking.baselibrary.rx.BaseException
import com.yking.usercenter.data.repository.UserRepository
import com.yking.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class UserServiceImpl @Inject constructor(): UserService {
    @Inject
    lateinit var repository:UserRepository
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile,pwd,verifyCode)
                .flatMap(object :Func1<BaseResp<String>,Observable<Boolean>>{
                    override fun call(t: BaseResp<String>): Observable<Boolean> {
                        if(t.status!=0){
                            return Observable.error(BaseException(t.status,t.message))
                        }
                        return Observable.just(true)
                    }
                })
    }
}