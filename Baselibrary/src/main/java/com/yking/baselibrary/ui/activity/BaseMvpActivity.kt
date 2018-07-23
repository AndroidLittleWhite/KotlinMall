package com.yking.baselibrary.ui.activity

import android.os.Bundle
import com.yking.baselibrary.common.BaseApplication
import com.yking.baselibrary.injection.component.ActivityComponent
import com.yking.baselibrary.injection.component.DaggerActivityComponent
import com.yking.baselibrary.injection.module.ActivityModule
import com.yking.baselibrary.injection.module.LifecycleProviderModule
import com.yking.baselibrary.presenter.BasePresenter
import com.yking.baselibrary.presenter.view.BaseView
import com.yking.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/17.
 */
open abstract class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView {


    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent:ActivityComponent

    private lateinit var progressLoading:ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        progressLoading= ProgressLoading.create(this)
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent= DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
    override fun showLoading() {
        progressLoading.showLoading()
    }

    override fun hideLoading() {
        progressLoading.hideLoading()
    }

    override fun onError(text:String) {
        toast(text)
    }
}