package com.kotlin.base.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides

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