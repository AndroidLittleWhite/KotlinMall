package com.yking.baselibrary.presenter

import com.trello.rxlifecycle.LifecycleProvider
import com.yking.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/17.
 */
open class BasePresenter<T:BaseView> {
    lateinit var mView:T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}