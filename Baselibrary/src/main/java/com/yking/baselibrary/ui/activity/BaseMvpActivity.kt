package com.yking.baselibrary.ui.activity

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
}