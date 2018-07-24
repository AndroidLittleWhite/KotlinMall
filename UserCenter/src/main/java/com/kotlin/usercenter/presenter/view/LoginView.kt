package com.kotlin.usercenter.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.usercenter.data.protocol.UserInfo

/**
 * @author Mr_YKing on 2018/7/18.
 */
interface LoginView : BaseView {
    fun onLoginResult(user: UserInfo)
}