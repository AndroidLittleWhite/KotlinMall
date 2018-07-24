package com.kotlin.usercenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.usercenter.data.protocol.UserInfo
import com.kotlin.usercenter.presenter.view.LoginView
import com.kotlin.usercenter.service.UserService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserService

    fun login(name: String, pwd: String,pushId:String) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        userService.login(name, pwd, pushId)
                .execute(object : BaseSubscribe<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                            mView.onLoginResult(t)
                    }
                }, lifecycleProvider)
    }
}