package com.kotlin.goods.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.goods.injection.module.CartModule
import com.kotlin.goods.ui.fragment.CartFragment
import dagger.Component

/**
 * Created by  on 2018/7/18.YaoKai
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CartModule::class))
interface CartComponet {
    fun  inject(fragment:CartFragment)
}