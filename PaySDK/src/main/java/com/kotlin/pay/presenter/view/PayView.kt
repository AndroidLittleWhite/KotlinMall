package com.kotlin.pay.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * @author Mr_YKing on 2018/7/18.
 */
interface PayView : BaseView {
    fun onGetSignResult(result:String)
    fun onPayOrderResult(result:Boolean)
}