package com.kotlin.goods.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.data.repository.CartRepository
import com.kotlin.goods.service.CartService
import rx.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class CartServiceImpl @Inject constructor() : CartService {



    @Inject
    lateinit var repository: CartRepository

    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
        return repository.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice,goodsCount,goodsSku).convert()
    }

    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }
}