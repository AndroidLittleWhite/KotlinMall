package com.yking.usercenter.presenter

import com.yking.baselibrary.ext.execute
import com.yking.baselibrary.presenter.BasePresenter
import com.yking.baselibrary.rx.BaseSubscribe
import com.yking.usercenter.presenter.view.RegisterView
import com.yking.usercenter.service.UserService
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