package com.kotlin.usercenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.usercenter.presenter.view.ForgetPwdView
import com.kotlin.usercenter.service.UserService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {
    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        userService.forgetPwd(mobile, verifyCode)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t) {
                            mView.onForgetPedResult("验证成功")
                        }
                    }
                }, lifecycleProvider)
    }
}