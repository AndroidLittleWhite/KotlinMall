package com.kotlin.base.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.base.injection.component.DaggerActivityComponent
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.widgets.ProgressLoading
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

        ARouter.getInstance().inject(this)
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