package com.yking.baselibrary.ext

import com.yking.baselibrary.rx.BaseSubscribe
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by  on 2018/7/18.YaoKai
 */
fun <T>Observable<T>.execute(subscribe:BaseSubscribe<T>){
    this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscribe)
}