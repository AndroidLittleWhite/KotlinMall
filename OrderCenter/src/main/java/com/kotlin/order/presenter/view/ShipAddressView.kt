package com.kotlin.order.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.data.protocol.ShipAddress

/**
 * @author Mr_YKing on 2018/7/18.
 */
interface ShipAddressView : BaseView {
    fun onGetShipAddressResult(result:MutableList<ShipAddress>?)
    fun onSetDefaultShipAddressResult(result:Boolean)
    fun onDeleteShipAddressResult(result:Boolean)
}