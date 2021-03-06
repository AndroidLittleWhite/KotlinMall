package com.kotlin.order.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.loading
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.order.R
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.injection.component.DaggerOrderComponet
import com.kotlin.order.injection.module.OrderModule
import com.kotlin.order.presenter.OrderListPresenter
import com.kotlin.order.presenter.view.OrderListView
import com.kotlin.order.ui.activity.OrderDetailActivity
import com.kotlin.order.ui.adapter.OrderAdapter
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Created by  on 2018/8/7.YaoKai
 */
class OrderFragment:BaseMvpFragment<OrderListPresenter>(),OrderListView {

    private lateinit var mAdapter:OrderAdapter
    override fun injectComponent() {
        DaggerOrderComponet.builder().activityComponent(activityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_order,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onStart() {
        super.onStart()
        mMultiStateView.loading()
        loadData()
    }


    private fun initView() {
        mOrderRv.layoutManager= LinearLayoutManager(context)
        mAdapter= OrderAdapter(activity!!)
        mOrderRv.adapter=mAdapter

        mAdapter.listener=object :OrderAdapter.OnOptClickListener{
            override fun onOptClick(optType: Int, order: Order) {
                when(optType){
                    OrderConstant.OPT_ORDER_CONFIRM->{
                        mPresenter.confirmOrder(order.id)
                    }
                    OrderConstant.OPT_ORDER_PAY->{
                        ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                                .withInt(ProviderConstant.KEY_ORDER_ID,order.id)
                                .withLong(ProviderConstant.KEY_ORDER_PRICE,order.totalPrice)
                                .navigation()
                    }
                    OrderConstant.OPT_ORDER_CANCEL->{
                        showCancelDidlog(order)
                    }
                }
            }
        }

        mAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter.OnItemClickListener<Order>{
            override fun onItemClick(item: Order, position: Int) {
                startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }
        })
    }

    private fun showCancelDidlog(order: Order) {
        AlertView("取消订单", "确定取消订单？", "取消", null,
                arrayOf("确定"), activity, AlertView.Style.Alert,
                OnItemClickListener { o, position ->
                    when (position) {
                        0 -> {
                            mPresenter.cancelOrder(order.id)
                        }
                    }
                }).show()
    }

    private fun loadData() {
        mPresenter.getOrderList(arguments!!.getInt(OrderConstant.KEY_ORDER_STATUS,OrderStatus.ORDER_ALL))
    }
    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            mAdapter!!.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
    override fun onConfirmOrderResult(result: Boolean) {
        if (result){
            toast("确认收货成功")
            loadData()
        }else{
            toast("确认收货失败，请稍后重试")
        }
    }

    override fun onCancelOrderResult(result: Boolean) {
        if (result){
            toast("取消订单成功")
            loadData()
        }else{
            toast("取消订单失败，请稍后重试")
        }
    }
}