package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.presenter.view.ShipAddressView
import com.kotlin.order.service.ShipAddressService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {
    @Inject
    lateinit var shipAddressService: ShipAddressService

    fun getShipAddress() {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        shipAddressService.getShipAddressList()
                .execute(object : BaseSubscribe<MutableList<ShipAddress>?>(mView) {
                    override fun onNext(t: MutableList<ShipAddress>?) {
                            mView.onGetShipAddressResult(t)
                    }
                }, lifecycleProvider)
    }

    fun setDefaultShipAddress(address:ShipAddress) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        shipAddressService.editShipAddress(address)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                            mView.onSetDefaultShipAddressResult(t)
                    }
                }, lifecycleProvider)
    }
}