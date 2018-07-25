package com.kotlin.usercenter.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.usercenter.data.protocol.UserInfo

/**
 * @author Mr_YKing on 2018/7/18.
 */
interface UserInfoView : BaseView {
    fun onGetUploadTokenResult(token:String)
    fun onEditUserResult(user:UserInfo)
}