package com.kotlin.usercenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.usercenter.presenter.view.ResetPwdView
import com.kotlin.usercenter.service.UserService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {
    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, verifyCode: String) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        userService.resetPwd(mobile, verifyCode)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t) {
                            mView.onResetPedResult("修改密码成功")
                        }
                    }
                }, lifecycleProvider)
    }
}