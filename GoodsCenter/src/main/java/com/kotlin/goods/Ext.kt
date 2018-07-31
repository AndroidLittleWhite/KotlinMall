package com.kotlin.goods

import android.widget.EditText
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.event.UpdateCartSizeEvent
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton
import ren.qinc.numberbutton.R

/*
    三方控件扩展
 */
fun NumberButton.getEditText():EditText{
    return find(R.id.text_count)
}
fun updateCartSize(mutableList: MutableList<CartGoods>?){
    val cartSize=mutableList?.map { it.goodsCount }?.sum()?:0
    AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE,cartSize)
    Bus.send(UpdateCartSizeEvent())
}