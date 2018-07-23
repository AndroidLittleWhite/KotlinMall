package com.yking.baselibrary.presenter

import android.content.Context
import com.kotlin.base.utils.NetWorkUtils
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

    @Inject
    lateinit var context:Context
    fun checkNetWork():Boolean{
        return NetWorkUtils.isNetWorkAvailable(context)
    }
}