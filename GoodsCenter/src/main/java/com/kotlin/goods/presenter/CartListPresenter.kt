package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscribe
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.presenter.view.CartListView
import com.kotlin.goods.service.CartService
import javax.inject.Inject

/**
 * @author Mr_YKing on 2018/7/18.
 */
class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {
    @Inject
    lateinit var cartService: CartService

    fun getCartList() {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        cartService.getCartList()
                .execute(object : BaseSubscribe<MutableList<CartGoods>?>(mView) {
                    override fun onNext(t: MutableList<CartGoods>?) {
                            mView.onGetCartListResult(t)
                    }
                }, lifecycleProvider)
    }
    fun deleteCartList(list: List<Int>) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        cartService.deleteCartList(list)
                .execute(object : BaseSubscribe<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                            mView.onDeleteCartListResult(t)
                    }
                }, lifecycleProvider)
    }
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        cartService.submitCart(list,totalPrice)
                .execute(object : BaseSubscribe<Int>(mView) {
                    override fun onNext(t: Int) {
                            mView.onSubmitCartListResult(t)
                    }
                }, lifecycleProvider)
    }
}