package com.kotlin.usercenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.usercenter.data.protocol.UserInfo
import com.kotlin.usercenter.presenter.view.UserInfoView
import com.kotlin.usercenter.service.UploadService
import com.kotlin.usercenter.service.UserService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {
    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken(){
        if (!checkNetWork()) {
            return
        }
        uploadService.getUploadToken().execute(object : BaseSubscribe<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)
    }

    fun editUser( userIcon:String,userName:String,userGender:String,userSign:String){
        if (!checkNetWork()) {
            return
        }
        userService.editUser(userIcon,userName,userGender,userSign).execute(object :BaseSubscribe<UserInfo>(mView){
            override fun onNext(t: UserInfo) {
                super.onNext(t)
                mView.onEditUserResult(t)
            }
        },lifecycleProvider)
    }
}