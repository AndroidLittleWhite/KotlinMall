package com.kotlin.base.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.kennyc.view.MultiStateView
import com.kotlin.base.R
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.rx.BaseFunc
import com.kotlin.base.rx.BaseFuncBoolean
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.base.utils.GlideUtils
import com.kotlin.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.find
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

fun ImageView.loadUrl(url: String){
    GlideUtils.loadUrlImage(context,url,this)
}

fun MultiStateView.loading(){
    viewState=MultiStateView.VIEW_STATE_LOADING
    val drawable = getView(MultiStateView.VIEW_STATE_LOADING)!!.find<View>(R.id.loading_anim_view).background
    (drawable as AnimationDrawable).start()
}

fun View.setVisible(visible:Boolean){
    visibility=if(visible) View.VISIBLE else View.GONE
}