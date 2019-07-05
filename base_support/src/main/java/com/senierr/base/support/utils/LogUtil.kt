package com.senierr.base.support.utils

import android.util.Log

/**
 * 日志工具类
 *
 * @author zhouchunjie
 * @date 2017/10/26
 */
object LogUtil {

    var isDebug = false
    var tag: String = LogUtil::class.java.simpleName

    fun logV(msg: String) {
        if (isDebug) {
            Log.v(tag, msg)
        }
    }

    fun logD(msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }

    fun logI(msg: String) {
        if (isDebug) {
            Log.i(tag, msg)
        }
    }

    fun logW(msg: String) {
        if (isDebug) {
            Log.w(tag, msg)
        }
    }

    fun logE(msg: String) {
        if (isDebug) {
            Log.e(tag, msg)
        }
    }

    fun logV(tag: String, msg: String) {
        if (isDebug) {
            Log.v(tag, msg)
        }
    }

    fun logD(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }

    fun logI(tag: String, msg: String) {
        if (isDebug) {
            Log.i(tag, msg)
        }
    }

    fun logW(tag: String, msg: String) {
        if (isDebug) {
            Log.w(tag, msg)
        }
    }

    fun logE(tag: String, msg: String) {
        if (isDebug) {
            Log.e(tag, msg)
        }
    }
}
