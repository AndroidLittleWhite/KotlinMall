package com.kotlin.base.rx

import com.kotlin.base.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by  on 2018/7/18.YaoKai
 */
open class BaseSubscribe<T>(val baseView:BaseView):Subscriber<T>() {
    override fun onStart() {
        baseView.showLoading()
    }
    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if (e is BaseException) {
            baseView.onError(e.msg)
        }else{
            e!!.printStackTrace()
        }
    }
    override fun onCompleted() {
        baseView.hideLoading()
    }
    override fun onNext(t: T) {
    }
}