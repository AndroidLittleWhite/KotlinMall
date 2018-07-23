package com.yking.baselibrary.presenter.view

/**
 * @author Mr_YKing on 2018/7/17.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
}