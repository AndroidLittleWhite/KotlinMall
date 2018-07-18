package com.yking.baselibrary.rx

import rx.Subscriber

/**
 * Created by  on 2018/7/18.YaoKai
 */
open class BaseSubscribe<T>:Subscriber<T>() {
    override fun onError(e: Throwable?) {
    }
    override fun onCompleted() {
    }
    override fun onNext(t: T) {
    }
}