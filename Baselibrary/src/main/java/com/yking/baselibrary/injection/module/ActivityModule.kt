package com.yking.baselibrary.injection.module

import android.app.Activity
import android.content.Context
import com.yking.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Mr_YKing on 2018/7/18.
 */
@Module
class ActivityModule(private val activity:Activity) {
    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}