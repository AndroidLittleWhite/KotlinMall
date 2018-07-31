package com.kotlin.goods.service

import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface CartService {
    /*
        添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>
}