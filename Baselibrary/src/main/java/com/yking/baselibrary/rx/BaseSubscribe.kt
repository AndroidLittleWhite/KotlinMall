package com.yking.baselibrary.rx

import com.yking.baselibrary.presenter.view.BaseView
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
    }
    override fun onCompleted() {
        baseView.hideLoading()
    }
    override fun onNext(t: T) {
    }
}