package com.senierr.base.support.ui

import android.app.Application
import android.util.Log
import com.senierr.base.support.utils.ActivityUtil
import com.senierr.base.support.utils.LogUtil
import io.reactivex.plugins.RxJavaPlugins

/**
 * 应用基类
 *
 * @author zhouchunjie
 * @date 2019/6/17 13:31
 */
abstract class BaseApplication : Application() {

    abstract fun isDebug(): Boolean

    override fun onCreate() {
        super.onCreate()

        // 初始化工具类
        ActivityUtil.bindToApplication(this)
        LogUtil.isDebug = isDebug()
        LogUtil.tag = javaClass.simpleName

        // 初始化RxJava
        RxJavaPlugins.setErrorHandler {
            LogUtil.logW(Log.getStackTraceString(it))
        }
    }
}