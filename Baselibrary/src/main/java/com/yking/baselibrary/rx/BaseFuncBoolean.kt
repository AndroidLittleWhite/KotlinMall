package com.yking.baselibrary.rx

import com.kotlin.base.common.ResultCode
import com.yking.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by  on 2018/7/20.YaoKai
 */
class BaseFuncBoolean<T>:Func1<BaseResp<T>,Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if(t.status!= ResultCode.SUCCESS){
            return Observable.error(BaseException(t.status,t.message))
        }
        return Observable.just(true)
    }
}