package com.yking.baselibrary.injection.component

import android.content.Context
import com.yking.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author Mr_YKing on 2018/7/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context():Context
}