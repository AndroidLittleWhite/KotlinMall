package com.kotlin.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * @author Mr_YKing on 2018/7/18.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class PerComponentScope