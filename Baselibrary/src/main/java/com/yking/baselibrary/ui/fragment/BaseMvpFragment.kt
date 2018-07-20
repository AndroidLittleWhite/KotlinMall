package com.yking.baselibrary.ui.fragment

import android.os.Bundle
import com.yking.baselibrary.common.BaseApplication
import com.yking.baselibrary.injection.component.ActivityComponent
import com.yking.baselibrary.injection.component.DaggerActivityComponent
import com.yking.baselibrary.injection.module.ActivityModule
import com.yking.baselibrary.injection.module.LifecycleProviderModule
import com.yking.baselibrary.presenter.BasePresenter
import com.yking.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/17.
 */
open abstract class BaseMvpFragment<T: BasePresenter<*>>: BaseFragment(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
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