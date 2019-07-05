package com.senierr.base.support.rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.ConcurrentHashMap

/**
 * Rx事件总栈
 *
 * @author zhouchunjie
 * @date 2019/6/17 14:47
 */
object RxBus {

    const val TAG_DEFAULT = "tag_default"

    private val bus = PublishSubject.create<Any>().toSerialized()
    private val stickyEventMap = ConcurrentHashMap<Class<*>, Any>()

    /**
     * 发送普通消息
     */
    fun post(message: Any) {
        bus.onNext(message)
    }

    /**
     * 订阅普通消息
     */
    fun <T> toObservable(eventType: Class<T>): Observable<T> {
        return bus.ofType(eventType)
    }

    /**
     * 发送粘性消息
     */
    fun postSticky(event: Any) {
        synchronized(stickyEventMap) {
            stickyEventMap.put(event.javaClass, event)
        }
        post(event)
    }

    /**
     * 订阅粘性消息
     */
    fun <T> toObservableSticky(eventType: Class<T>): Observable<T> {
        synchronized(stickyEventMap) {
            val observable = bus.ofType(eventType)
            val event = stickyEventMap[eventType]

            return if (event != null) {
                observable.mergeWith(Observable.create {
                    val item = eventType.cast(event)
                    if (item != null) it.onNext(item)
                })
            } else {
                observable
            }
        }
    }

    /**
     * 根据eventType获取Sticky事件
     */
    fun <T> getStickyEvent(eventType: Class<T>): T? {
        synchronized(stickyEventMap) {
            return eventType.cast(stickyEventMap[eventType])
        }
    }

    /**
     * 移除指定eventType的Sticky事件
     */
    fun <T> removeStickyEvent(eventType: Class<T>): T? {
        synchronized(stickyEventMap) {
            return eventType.cast(stickyEventMap.remove(eventType))
        }
    }

    /**
     * 移除所有的Sticky事件
     */
    fun removeAllStickyEvents() {
        synchronized(stickyEventMap) {
            stickyEventMap.clear()
        }
    }
}