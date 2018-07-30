package com.kotlin.goods.service

import com.kotlin.goods.data.protocol.Goods
import rx.Observable

/**
 * Created by  on 2018/7/18.YaoKai
 */
interface GoodsService {
    fun getGoodsList(categoryId:Int,pageNum:Int): Observable<MutableList<Goods>?>
    fun getGoodsListByKeyword(keyWord: String, pageNum: Int): Observable<MutableList<Goods>?>
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}