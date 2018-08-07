package com.kotlin.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.loading
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.R
import com.kotlin.order.common.OrderConstant.Companion.KEY_SHIP_ADDRESS
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.event.SelectAddressEvent
import com.kotlin.order.injection.component.DaggerShipAddressComponet
import com.kotlin.order.injection.module.ShipAddressModule
import com.kotlin.order.presenter.ShipAddressPresenter
import com.kotlin.order.presenter.view.ShipAddressView
import com.kotlin.order.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by  on 2018/8/6.YaoKai
 */
class ShipAddressActivity:BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {



    private lateinit var mAdapter: ShipAddressAdapter

    override fun injectComponent() {
        DaggerShipAddressComponet.builder().activityComponent(activityComponent).shipAddressModule(ShipAddressModule()).build().inject(this)
        mPresenter.mView = this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        mMultiStateView.loading()
        mPresenter.getShipAddress()
    }

    private fun initView() {
        mAddressRv.layoutManager=LinearLayoutManager(this)
        mAdapter=ShipAddressAdapter(this)
        mAddressRv.adapter=mAdapter
        mAdapter.mOptClickListener=object : ShipAddressAdapter.OnOptClickListener{
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.setDefaultShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<ShipAddressEditActivity>(KEY_SHIP_ADDRESS to address)
            }

            override fun onDelete(address: ShipAddress) {
                AlertView("删除", "确定删除地址？", "取消", null,
                        arrayOf("确定"), this@ShipAddressActivity, AlertView.Style.Alert,
                        OnItemClickListener { o, position ->
                            when (position) {
                                0 -> {
                                  mPresenter.deleteShipAddress(address.id)
                                }
                            }
                        }).show()
            }
        }

        mAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter.OnItemClickListener<ShipAddress>{
            override fun onItemClick(item: ShipAddress, position: Int) {
                Bus.send(SelectAddressEvent(item))
                finish()
            }

        })
        mAddAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()
        }
    }
    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
    override fun onSetDefaultShipAddressResult(result: Boolean) {
        if (result){
            toast("设置默认地址成功")
            loadData()
        }else{
            toast("设置默认地址失败，请稍后再试")
        }
    }
    override fun onDeleteShipAddressResult(result: Boolean) {
        if (result){
            toast("删除成功")
            loadData()
        }else{
            toast("删除失败，请稍后再试")
        }
    }
}