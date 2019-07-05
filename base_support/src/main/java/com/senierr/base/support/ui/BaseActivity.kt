package com.senierr.base.support.ui

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.ConcurrentHashMap

/**
 * Activity基类
 *
 * @author zhouchunjie
 * @date 2018/5/28
 */
open class BaseActivity : AppCompatActivity() {

    // 无标签订阅集
    private val compositeDisposable = CompositeDisposable()
    // 有标签订阅集
    private val disposableMap = ConcurrentHashMap<String, Disposable>()

    override fun onDestroy() {
        disposeAll()
        super.onDestroy()
    }

    /**
     * 绑定至容器
     */
    protected fun Disposable.bindToLifecycle() {
        compositeDisposable.add(this)
    }

    /**
     * 绑定至容器，带标签
     */
    protected fun Disposable.bindToLifecycle(tag: String) {
        disposableMap[tag] = this
    }

    /**
     * 取消Tag标签订阅
     */
    protected fun dispose(tag: String) {
        disposableMap[tag]?.dispose()
    }

    /**
     * 取消所有订阅
     */
    protected fun disposeAll() {
        compositeDisposable.clear()
        disposableMap.forEach {
            it.value.dispose()
        }
    }
}