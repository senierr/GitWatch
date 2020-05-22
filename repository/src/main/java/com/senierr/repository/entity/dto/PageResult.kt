package com.senierr.repository.entity.dto

/**
 * 分页数据
 *
 * @author zhouchunjie
 * @date 2020/5/22
 */
data class PageResult<T>(
    val curPage: Int = 0,
    val datas: MutableList<T> = mutableListOf(),
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
)