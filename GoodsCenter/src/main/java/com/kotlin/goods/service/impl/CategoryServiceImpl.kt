package com.kotlin.goods.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.repository.CategoryRepository
import com.kotlin.goods.service.CategroyService
import rx.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/7/18.YaoKai
 */
class CategoryServiceImpl @Inject constructor(): CategroyService {

    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategroy(parentId: Int): Observable<MutableList<Category>?> {
       return  repository.getCategory(parentId).convert()
    }
}