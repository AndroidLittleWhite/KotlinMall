package com.kotlin.pay.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.pay.R
import com.kotlin.pay.injection.component.DaggerPayComponent
import com.kotlin.pay.injection.module.PayModule
import com.kotlin.pay.presenter.PayPresenter
import com.kotlin.pay.presenter.view.PayView
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_cash_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Created by  on 2018/8/8.YaoKai
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity: BaseMvpActivity<PayPresenter>(),PayView ,View.OnClickListener{



    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId:Int=0

    @Autowired(name = ProviderConstant.KEY_ORDER_PRICE)
    @JvmField
    var mTotalPrice:Long=0

    override fun injectComponent() {
        DaggerPayComponent.builder().activityComponent(activityComponent).payModule(PayModule()).build().inject(this)
        mPresenter.mView = this   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)

        initView()
    }

    private fun initView() {
        mAlipayTypeTv.isSelected=true
        mAlipayTypeTv.onClick(this)
        mWeixinTypeTv.onClick(this)
        mBankCardTypeTv.onClick(this)
        mPayBtn.onClick(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.mAlipayTypeTv->{updateCheckStatus(true,false,false)}
            R.id.mWeixinTypeTv->{updateCheckStatus(false,true,false)}
            R.id.mBankCardTypeTv->{updateCheckStatus(false,false,true)}
            R.id.mPayBtn->{
                mPresenter.getPaySign(mOrderId,mTotalPrice)
            }
        }
    }
    private fun updateCheckStatus(isAliPay:Boolean,isWenxinPay:Boolean,isBankCardPay:Boolean){
        mAlipayTypeTv.isSelected=isAliPay
        mWeixinTypeTv.isSelected=isWenxinPay
        mBankCardTypeTv.isSelected=isBankCardPay
    }
    override fun onGetSignResult(result: String) {
        doAsync {
            val payMap:Map<String,String> = PayTask(this@CashRegisterActivity).payV2(result, true)
            uiThread {
                if (payMap["resultStatus"].equals("9000")){
                   mPresenter.payOrder(mOrderId)
                }else{
                    toast("支付失败：${payMap["memo"]}")
                }
            }
        }
    }
    override fun onPayOrderResult(result: Boolean) {
        toast("支付成功")
        finish()
    }
}