package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.order.presenter.view.EditShipAddressView
import com.kotlin.order.service.ShipAddressService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {
    @Inject
    lateinit var shipAddressService: ShipAddressService

    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        shipAddressService.addShipAddress(shipUserName,shipUserMobile,shipAddress)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                            mView.onAddAddressResult(t)
                    }
                }, lifecycleProvider)
    }
}