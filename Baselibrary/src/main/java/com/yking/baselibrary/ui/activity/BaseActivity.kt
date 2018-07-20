package com.yking.baselibrary.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.yking.baselibrary.common.AppManager

/**
 * @author Mr_YKing on 2018/7/17.
 */
open class BaseActivity:RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}