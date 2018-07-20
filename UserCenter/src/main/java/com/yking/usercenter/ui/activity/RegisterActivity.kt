package com.yking.usercenter.ui.activity

import android.os.Bundle
import com.yking.baselibrary.common.AppManager
import com.yking.baselibrary.ext.onClick
import com.yking.baselibrary.ui.activity.BaseMvpActivity
import com.yking.usercenter.R
import com.yking.usercenter.injection.component.DaggerUserComponet
import com.yking.usercenter.injection.module.UserModule
import com.yking.usercenter.presenter.RegisterPresenter
import com.yking.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    var firstPress:Long =0
    override fun injectComponent() {
        DaggerUserComponet.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mBtRegister.onClick {
            mPresenter.register(mEtMobile.text.toString(), mEtVerifyCode.text.toString(), mEtPwd.text.toString())
        }
    }

    override fun onRegisterSuccess(msg:String) {
        toast(msg)
    }

    override fun onBackPressed() {
        val currentTime=System.currentTimeMillis()
        if (currentTime - firstPress > 2000) {
            toast("再按一次退出程序")
            firstPress=currentTime
        }else{
            AppManager.instance.exitApp(this)
        }
    }
}
