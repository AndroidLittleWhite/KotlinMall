package com.kotlin.order.ui.activity

import android.os.Bundle
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.order.R
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.injection.component.DaggerShipAddressComponet
import com.kotlin.order.injection.module.ShipAddressModule
import com.kotlin.order.presenter.EditShipAddressPresenter
import com.kotlin.order.presenter.view.EditShipAddressView
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * Created by  on 2018/8/6.YaoKai
 */
class ShipAddressEditActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {


    private var mAddress: ShipAddress? = null
    override fun injectComponent() {
        DaggerShipAddressComponet.builder().activityComponent(activityComponent).shipAddressModule(ShipAddressModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
        initData()
    }


    private fun initView() {
        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()) {
                toast("请输入收货人名称")
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()) {
                toast("请输入联系电话")
                return@onClick
            }
            if (mShipAddressEt.text.isNullOrEmpty()) {
                toast("请输入收货地址")
                return@onClick
            }
            if (mAddress==null){
                mPresenter.addShipAddress(mShipNameEt.text.toString(), mShipMobileEt.text.toString(), mShipAddressEt.text.toString())
            }else{
                mAddress!!.shipUserName=mShipNameEt.text.toString()
                mAddress!!.shipUserMobile=mShipMobileEt.text.toString()
                mAddress!!.shipAddress=mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }
        }
    }

    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }
    }

    override fun onAddAddressResult(result: Boolean) {
        if (result) {
            toast("保存成功")
            finish()
        } else {
            toast("保存失败，请稍后再试")
        }
    }
    override fun onSetDefaultShipAddressResult(result: Boolean) {
        if (result) {
            toast("修改成功")
            finish()
        } else {
            toast("修改失败，请稍后再试")
        }
    }
}