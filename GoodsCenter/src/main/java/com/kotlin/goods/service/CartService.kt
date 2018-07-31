package com.kotlin.goods.service

import com.kotlin.goods.data.protocol.CartGoods
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

    /*
       获取购物车列表
    */
    fun getCartList(): Observable<MutableList<CartGoods>?>

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>
}