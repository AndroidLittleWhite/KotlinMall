package com.yking.usercenter.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import com.yking.baselibrary.ui.activity.BaseMvpActivity
import com.yking.usercenter.R
import com.yking.usercenter.R.id.mBtRegister
import com.yking.usercenter.presenter.RegisterPresenter
import com.yking.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter=RegisterPresenter()
        mPresenter.mView=this
        
        mBtRegister.setOnClickListener{
            mPresenter.register("","")
        }
    }

    override fun onRegisterSuccess() {
        toast("注册成功")
    }
}
