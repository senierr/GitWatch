package com.senierr.base.support.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.ConcurrentHashMap

/**
 * MVP-Presenter基类
 *
 * @author zhouchunjie
 * @date 2019/5/30 11:42
 */
abstract class BasePresenter<V: BaseView> {

    // 无标签订阅集
    private val compositeDisposable = CompositeDisposable()
    // 有标签订阅集
    private val disposableMap = ConcurrentHashMap<String, Disposable>()

    protected var view: V? = null

    /**
     * 绑定视图
     */
    open fun onAttach(view: V) {
        this.view = view
    }

    /**
     * 解除绑定视图
     */
    open fun onDetach() {
        disposeAll()
        view = null
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