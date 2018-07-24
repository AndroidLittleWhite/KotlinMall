package com.kotlin.usercenter.presenter

import com.kotlin.base.presenter.BasePresenter
import com.kotlin.usercenter.presenter.view.UserInfoView
import com.kotlin.usercenter.service.UserService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {
    @Inject
    lateinit var userService: UserService


}