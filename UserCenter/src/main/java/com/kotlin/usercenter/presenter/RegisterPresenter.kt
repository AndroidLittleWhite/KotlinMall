package com.kotlin.usercenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.usercenter.presenter.view.RegisterView
import com.kotlin.usercenter.service.UserService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserService

    fun register(name: String, verifyCode: String, pwd: String) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        userService.register(name, pwd, verifyCode)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t){}
                            mView.onRegisterSuccess("注册成功")
                    }
                }, lifecycleProvider)
    }
}