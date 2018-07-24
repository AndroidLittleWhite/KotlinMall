package com.kotlin.base.ui.fragment

import android.os.Bundle
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.base.injection.component.DaggerActivityComponent
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/17.
 */
open abstract class BaseMvpFragment<T: BasePresenter<*>>: BaseFragment(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text:String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()

        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent= DaggerActivityComponent.builder()
                .appComponent((activity?.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity!!))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}