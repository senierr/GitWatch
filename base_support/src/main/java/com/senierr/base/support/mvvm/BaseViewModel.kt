package com.senierr.base.support.mvvm

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.ConcurrentHashMap

/**
 * ViewModel基础类
 *
 * @author zhouchunjie
 * @date 2019/7/9
 */
open class BaseViewModel : ViewModel() {

    // 无标签订阅集
    private val compositeDisposable = CompositeDisposable()
    // 有标签订阅集
    private val disposableMap = ConcurrentHashMap<String, Disposable>()

    override fun onCleared() {
        disposeAll()
        super.onCleared()
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