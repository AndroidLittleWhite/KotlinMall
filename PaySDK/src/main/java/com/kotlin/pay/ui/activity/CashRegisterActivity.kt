package com.kotlin.pay.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.pay.R
import com.kotlin.provider.router.RouterPath

/**
 * Created by  on 2018/8/8.YaoKai
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)
    }
}