package com.kotlin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.loading
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.goods.R
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.injection.component.DaggerCartComponet
import com.kotlin.goods.injection.module.CartModule
import com.kotlin.goods.presenter.CartListPresenter
import com.kotlin.goods.presenter.view.CartListView
import com.kotlin.goods.ui.adapter.CartGoodsAdapter
import kotlinx.android.synthetic.main.fragment_cart.*

/*
    商品详情Tab One
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {

    private lateinit var mCartAdapter:CartGoodsAdapter

    override fun injectComponent() {
        DaggerCartComponet.builder().activityComponent(activityComponent).cartModule(CartModule()).build().inject(this)
        mPresenter.mView = this
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager=LinearLayoutManager(context)
        mCartAdapter= CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter=mCartAdapter
    }

    private fun loadData() {
        mMultiStateView.loading()
        mPresenter.getCartList()
    }
    override fun onGetCartListResult(mutableList: MutableList<CartGoods>?) {
        if (mutableList != null && mutableList.size > 0) {
            mCartAdapter.setData(mutableList)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}