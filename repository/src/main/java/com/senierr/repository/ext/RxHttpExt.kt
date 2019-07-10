package com.senierr.repository.ext

import com.senierr.http.builder.RequestBuilder
import com.senierr.repository.remote.ResponseConverter
import io.reactivex.Observable
import java.lang.reflect.Type

/**
 * 网络请求扩展
 *
 * @author zhouchunjie
 * @date 2019/7/10
 */

fun <T> RequestBuilder.asAsync(types: Array<Type>): Observable<T> {
    return toObservable(ResponseConverter(types))
}