package com.kotlin.base.injection.module

import android.content.Context
import com.kotlin.base.common.BaseApplication
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