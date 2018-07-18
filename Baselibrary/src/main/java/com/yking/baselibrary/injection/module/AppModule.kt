package com.yking.baselibrary.injection.module

import android.content.Context
import com.yking.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Mr_YKing on 2018/7/18.
 */
@Module
class AppModule(private val context:BaseApplication) {
    @Provides
    @Singleton
    fun providesContext():Context{
        return context
    }
}