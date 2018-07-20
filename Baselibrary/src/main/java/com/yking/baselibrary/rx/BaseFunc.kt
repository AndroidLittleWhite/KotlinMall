package com.yking.baselibrary.rx

import com.yking.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by  on 2018/7/20.YaoKai
 */
class BaseFunc<T>: Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if(t.status!=0){
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}