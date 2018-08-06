package com.kotlin.order.injection.module

import com.kotlin.order.service.ShipAddressService
import com.kotlin.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/7/18.YaoKai
 */
@Module
class ShipAddressModule {
    @Provides
    fun providesShipAddressService(service: ShipAddressServiceImpl): ShipAddressService {
       return service
    }
}