package com.yking.baselibrary.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.yking.baselibrary.common.BaseApplication
import com.yking.baselibrary.injection.component.ActivityComponent
import com.yking.baselibrary.injection.component.DaggerActivityComponent
import com.yking.baselibrary.injection.module.ActivityModule
import com.yking.baselibrary.presenter.BasePresenter
import com.yking.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/17.
 */
open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent:ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()

    }

    private fun initActivityInjection() {
        activityComponent= DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }
}