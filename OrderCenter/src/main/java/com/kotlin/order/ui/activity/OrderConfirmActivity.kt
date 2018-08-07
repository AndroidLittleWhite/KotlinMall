package com.kotlin.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.ext.onClick
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.R
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.event.SelectAddressEvent
import com.kotlin.order.injection.component.DaggerOrderComponet
import com.kotlin.order.injection.module.OrderModule
import com.kotlin.order.presenter.OrderConfirmPresenter
import com.kotlin.order.presenter.view.OrderConfirmView
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 * Created by  on 2018/8/6.YaoKai
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity:BaseMvpActivity<OrderConfirmPresenter>(),OrderConfirmView {


    @Autowired(name =ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId:Int=0

    private lateinit var mAdapter:OrderGoodsAdapter

    private var mCurrentOrder:Order?=null
    override fun injectComponent() {
        DaggerOrderComponet.builder().activityComponent(activityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        loadData()
        initObserve()
    }

    private fun initView() {
        mOrderGoodsRv.layoutManager=LinearLayoutManager(this)
        mAdapter= OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter=mAdapter

        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }
        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }
        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {
                mPresenter.submitOrder(mCurrentOrder!!)
            }
        }
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }
    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe {
                    t: SelectAddressEvent ->
                    run {
                        mCurrentOrder?.let {
                            it.shipAddress=t.address
                        }
                        updateAddressView()
                    }
                }
                .registerInBus(this)

    }

    private fun updateAddressView() {
        mCurrentOrder?.let {
            if (it.shipAddress==null){
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            }else{
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)

                mShipNameTv.text=it.shipAddress!!.shipUserName+"   "+it.shipAddress!!.shipUserMobile
                mShipAddressTv.text=it.shipAddress!!.shipAddress
            }
        }
    }

    override fun onGetOrderByIdResult(result: Order) {
        mCurrentOrder=result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text="合计${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"

        updateAddressView()
    }
    override fun onSubmitResult(result: Boolean) {
        if(result){
            toast("1111")
        }else{
            toast("提交失败，请稍后重试")
        }
    }
    /*
       取消事件监听
    */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}