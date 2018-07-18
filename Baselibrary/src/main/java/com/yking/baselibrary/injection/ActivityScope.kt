package com.yking.baselibrary.injection

import java.lang.annotation.Documented
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME
import java.lang.annotation.Retention

/**
 * @author Mr_YKing on 2018/7/18.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope