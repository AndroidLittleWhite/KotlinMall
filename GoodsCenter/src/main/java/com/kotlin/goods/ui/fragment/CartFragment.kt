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
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.R
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.event.CartAllCheckedEvent
import com.kotlin.goods.event.UpdateTotalPriceEvent
import com.kotlin.goods.injection.component.DaggerCartComponet
import com.kotlin.goods.injection.module.CartModule
import com.kotlin.goods.presenter.CartListPresenter
import com.kotlin.goods.presenter.view.CartListView
import com.kotlin.goods.ui.adapter.CartGoodsAdapter
import com.kotlin.goods.updateCartSize
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/*
    商品详情Tab One
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {


    private lateinit var mCartAdapter:CartGoodsAdapter

    private var mTotalPrice:Long=0

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
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
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
            updateTotalPrice()
        }

        mHeaderBar.getRightView().onClick {
            val isEditStatues=mHeaderBar.getRightView().text==resources.getText(R.string.common_edit)

            mTotalPriceTv.setVisible(isEditStatues.not())
            mSettleAccountsBtn.setVisible(!isEditStatues)
            mDeleteBtn.setVisible(isEditStatues)

            mHeaderBar.getRightView().text=if (isEditStatues) resources.getText(R.string.common_complete)else resources.getText(R.string.common_edit)
        }

        mDeleteBtn.onClick {
            val deleteList= arrayListOf<Int>()
            mCartAdapter.dataList.filter { it.isSelected }.mapTo(deleteList){it.id}
            if (deleteList.size==0){
                toast("请选择要删除的商品")
            }else{
                mPresenter.deleteCartList(deleteList)
            }
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
                        updateTotalPrice()
                        mAllCheckedCb.isChecked=t.isAllChecked
                    }
                }
                .registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>()
                .subscribe {
                    t: UpdateTotalPriceEvent ->
                    run {
                        updateTotalPrice()
                    }
                }
                .registerInBus(this)

    }
    override fun onGetCartListResult(mutableList: MutableList<CartGoods>?) {
        if (mutableList != null && mutableList.size > 0) {
            mCartAdapter.setData(mutableList)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mAllCheckedCb.isChecked=false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        updateCartSize(mutableList)
        updateTotalPrice()
    }
    fun updateTotalPrice(){
        mTotalPrice=mCartAdapter.dataList.filter { it.isSelected }.map { it.goodsPrice*it.goodsCount }.sum()
        mTotalPriceTv.text="合计：${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }
    override fun onDeleteCartListResult(boolean: Boolean) {
        if (boolean){
            toast("删除成功")
            loadData()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}