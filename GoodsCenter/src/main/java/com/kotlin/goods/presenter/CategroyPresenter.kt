package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.presenter.view.CategroyView
import com.kotlin.goods.service.CategroyService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class CategroyPresenter @Inject constructor() : BasePresenter<CategroyView>() {
    @Inject
    lateinit var categoryService: CategroyService

    fun getCategory(parentId:Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        categoryService.getCategroy(parentId)
                .execute(object : BaseSubscribe<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                            mView.onGetCategoryResult(t)
                    }
                }, lifecycleProvider)
    }
}