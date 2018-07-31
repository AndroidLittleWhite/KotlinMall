package com.kotlin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.loading
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.goods.R
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.event.CartAllCheckedEvent
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
        initObserve()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager=LinearLayoutManager(context)
        mCartAdapter= CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter=mCartAdapter

        mAllCheckedCb.onClick {
            for(item in mCartAdapter.dataList){
                item.isSelected=mAllCheckedCb.isChecked
            }
            mCartAdapter.notifyDataSetChanged()
        }
    }

    private fun loadData() {
        mMultiStateView.loading()
        mPresenter.getCartList()
    }
    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
                .subscribe {
                    t: CartAllCheckedEvent ->
                    run {
                        mAllCheckedCb.isChecked=t.isAllChecked
                    }
                }
                .registerInBus(this)

    }
    override fun onGetCartListResult(mutableList: MutableList<CartGoods>?) {
        if (mutableList != null && mutableList.size > 0) {
            mCartAdapter.setData(mutableList)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}