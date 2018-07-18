package com.yking.usercenter.ui.activity

import android.os.Bundle
import com.yking.baselibrary.injection.component.ActivityComponent
import com.yking.baselibrary.ui.activity.BaseMvpActivity
import com.yking.usercenter.R
import com.yking.usercenter.injection.component.DaggerUserComponet
import com.yking.usercenter.injection.module.UserModule
import com.yking.usercenter.presenter.RegisterPresenter
import com.yking.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()
        mBtRegister.setOnClickListener {
            mPresenter.register(mEtMobile.text.toString(), mEtVerifyCode.text.toString(), mEtPwd.text.toString())
        }
    }

    private fun initInjection() {
        DaggerUserComponet.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterSuccess() {
        toast("注册成功")
    }
}
