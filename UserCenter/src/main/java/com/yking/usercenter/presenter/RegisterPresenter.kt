package com.yking.usercenter.presenter

import com.yking.baselibrary.presenter.BasePresenter
import com.yking.usercenter.presenter.view.RegisterView

/**
 * @author Mr_YKing on 2018/7/18.
 */
class RegisterPresenter:BasePresenter<RegisterView>(){
    fun register(name:String ,verifyCode: String){
        /**
         * 业务逻辑
         */
        mView.onRegisterSuccess()
    }
}