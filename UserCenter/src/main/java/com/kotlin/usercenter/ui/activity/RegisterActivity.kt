package com.kotlin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.common.AppManager
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.usercenter.injection.component.DaggerUserComponet
import com.kotlin.usercenter.injection.module.UserModule
import com.kotlin.usercenter.presenter.RegisterPresenter
import com.kotlin.usercenter.presenter.view.RegisterView
import com.yking.usercenter.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {


    private var firstPress:Long =0
    override fun injectComponent() {
        DaggerUserComponet.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()

    }

    private fun initView() {

        mRegisterBtn.enable(mMobileEt,{isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt,{isBtnEnable()})

        mRegisterBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)
    }


    override fun onClick(view: View) {
        when(view.id){
            R.id.mVerifyCodeBtn->{
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }
            R.id.mRegisterBtn->{
                mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
            }
        }
    }
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not()&&
                mVerifyCodeEt.text.isNullOrEmpty().not()&&
                mPwdEt.text.isNullOrEmpty().not()&&
                mPwdConfirmEt.text.isNullOrEmpty().not()
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
