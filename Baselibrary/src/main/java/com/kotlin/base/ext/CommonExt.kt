package com.kotlin.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.kotlin.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.rx.BaseFunc
import com.kotlin.base.rx.BaseFuncBoolean
import com.kotlin.base.rx.BaseSubscribe
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by  on 2018/7/18.YaoKai
 */
fun <T>Observable<T>.execute(subscribe:BaseSubscribe<T>,provider: LifecycleProvider<*>){
    this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
            .compose(provider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscribe)
}

fun <T>Observable<BaseResp<T>>.convert():Observable<T>{
    return this.flatMap(BaseFunc())
}
fun <T>Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFuncBoolean())
}
fun View.onClick(listener:View.OnClickListener){
    this.setOnClickListener(listener)
}

fun View.onClick(method:()->Unit){
    this.setOnClickListener { method() }
}

fun Button.enable(et:EditText,method: () -> Boolean){
    val btn=this
    et.addTextChangedListener(object :DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled=method()
        }
    })
}