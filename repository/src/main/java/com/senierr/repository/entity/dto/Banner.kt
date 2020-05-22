package com.senierr.repository.entity.dto

/**
 * 轮播图
 *
 * @author zhouchunjie
 * @date 2020/5/22
 */
data class Banner(
    val desc: String = "",
    val id: Long = 0,
    val imagePath: String = "",
    val isVisible: Int = 0,
    val order: Int = 0,
    val title: String = "",
    val type: Int = 0,
    val url: String = ""
)