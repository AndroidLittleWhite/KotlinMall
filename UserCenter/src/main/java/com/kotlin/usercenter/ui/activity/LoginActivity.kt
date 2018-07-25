package com.kotlin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.common.AppManager
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.utils.UserPrefsUtils.putUserInfo
import com.kotlin.usercenter.data.protocol.UserInfo
import com.kotlin.usercenter.injection.component.DaggerUserComponet
import com.kotlin.usercenter.injection.module.UserModule
import com.kotlin.usercenter.presenter.LoginPresenter
import com.kotlin.usercenter.presenter.view.LoginView
import com.yking.usercenter.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {


    private var firstPress:Long =0
    override fun injectComponent() {
        DaggerUserComponet.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

    }

    private fun initView() {

        mLoginBtn.enable(mMobileEt,{isBtnEnable()})
        mLoginBtn.enable(mPwdEt,{isBtnEnable()})

        mLoginBtn.onClick(this)
        mForgetPwdTv.onClick(this)
        mHeaderBar.getRightView().onClick(this)
    }


    override fun onClick(view: View) {
        when(view.id){
            R.id.mRightTv->{ startActivity<RegisterActivity>()}
            R.id.mLoginBtn ->{
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
            }
            R.id.mForgetPwdTv ->{
                startActivity<ForgetPwdActivity>()
            }
        }
    }
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not()&&
                mPwdEt.text.isNullOrEmpty().not()
    }

    override fun onLoginResult(user: UserInfo) {
        toast("登陆成功")
        putUserInfo(user)
        startActivity<UserInfoActivity>()
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