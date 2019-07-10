package com.senierr.repository.remote.entity

/**
 * 基础请求返回
 *
 * @author zhouchunjie
 * @date 2019/7/10
 */
data class HttpResponse<T>(
    val errorCode: Int? = null,
    val errorMsg: String? = null,
    val data: T? = null
)