package com.yking.baselibrary.data.protocol

/**
 * Created by  on 2018/7/18.YaoKai
 */
class BaseResp<T>(val status:Int,val message:String,val data:T)