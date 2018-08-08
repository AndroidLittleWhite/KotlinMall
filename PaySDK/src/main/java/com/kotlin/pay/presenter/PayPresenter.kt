package com.kotlin.pay.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.pay.presenter.view.PayView
import com.kotlin.pay.service.PayService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class PayPresenter @Inject constructor() : BasePresenter<PayView>() {
    @Inject
    lateinit var payService: PayService

    fun getPaySign(orderId: Int, totalPrice: Long) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        payService.getPaySign(orderId,totalPrice)
                .execute(object : BaseSubscribe<String>(mView) {
                    override fun onNext(t: String) {
                            mView.onGetSignResult(t)
                    }
                }, lifecycleProvider)
    }
    fun payOrder(orderId: Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        payService.payOrder(orderId)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                            mView.onPayOrderResult(t)
                    }
                }, lifecycleProvider)
    }
}