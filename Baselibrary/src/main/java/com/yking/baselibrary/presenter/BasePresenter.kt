package com.yking.baselibrary.presenter

import com.yking.baselibrary.presenter.view.BaseView

/**
 * @author Mr_YKing on 2018/7/17.
 */
open class BasePresenter<T:BaseView> {
    lateinit var mView:T
}