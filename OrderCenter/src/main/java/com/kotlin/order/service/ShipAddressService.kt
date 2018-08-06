package com.kotlin.order.service

import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface ShipAddressService {
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>
}