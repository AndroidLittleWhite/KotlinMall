package com.kotlin.base.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * @author Mr_YKing on 2018/7/18.
 */
@Module
class LifecycleProviderModule(private val provider: LifecycleProvider<*>) {
    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> {
        return provider
    }
}