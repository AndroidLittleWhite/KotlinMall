package com.kotlin.order.service

import com.kotlin.order.data.protocol.ShipAddress
import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface ShipAddressService {
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>

    /*
        获取收货地址列表
     */
    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>
    /*
       修改收货地址
    */
    fun editShipAddress(address:ShipAddress): Observable<Boolean>
}