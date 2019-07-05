package com.senierr.base.support.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.ConcurrentHashMap

/**
 * Fragment基类
 *
 * @author zhouchunjie
 * @date 2018/5/28
 */
open class BaseFragment : Fragment() {

    // 无标签订阅集
    private val compositeDisposable = CompositeDisposable()
    // 有标签订阅集
    private val disposableMap = ConcurrentHashMap<String, Disposable>()

    /**
     * 容器是否已初始化
     */
    protected var isActivityCreated = false
    /**
     * 是否已懒加载
     */
    protected var isLazyCreated = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isActivityCreated = true
        if (isActivityCreated && userVisibleHint && !isLazyCreated) {
            onLazyCreated()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isActivityCreated && isVisibleToUser && !isLazyCreated) {
            onLazyCreated()
        }
    }

    override fun onDestroyView() {
        disposeAll()
        super.onDestroyView()
    }

    override fun onDestroy() {
        disposeAll()
        super.onDestroy()
    }

    /**
     * 懒加载初始化
     */
    protected open fun onLazyCreated() {
        this.isLazyCreated = true
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