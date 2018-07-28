package com.kotlin.goods.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.data.repository.GoodsRepository
import com.kotlin.goods.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class GoodsServiceImpl @Inject constructor(): GoodsService {
    @Inject
    lateinit var repository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNum: Int): Observable<MutableList<Goods>?> {
        return  repository.getGoodsList(categoryId,pageNum).convert()
    }

}