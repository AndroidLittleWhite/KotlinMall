package com.kotlin.goods.service

import com.kotlin.goods.data.protocol.Category
import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface CategroyService {
    fun getCategroy(parentId:Int): Observable<MutableList<Category>?>
}