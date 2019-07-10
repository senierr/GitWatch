package com.senierr.repository.remote.entity

/**
 * 分页
 *
 * @author zhouchunjie
 * @date 2019/7/10 19:34
 */
data class Page<T>(
    var curPage: Int? = null,
    var datas: MutableList<T>? = null,
    var offset: Int? = null,
    var over: Boolean? = null,
    var pageCount: Int? = null,
    var size: Int? = null,
    var total: Int? = null
)
