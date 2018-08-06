package com.kotlin.order.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.order.injection.module.ShipAddressModule
import com.kotlin.order.ui.activity.ShipAddressActivity
import com.kotlin.order.ui.activity.ShipAddressEditActivity
import dagger.Component

/**
 * Created by  on 2018/7/18.YaoKai
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponet {
    fun inject(activity: ShipAddressActivity)
    fun inject(activity: ShipAddressEditActivity)
}