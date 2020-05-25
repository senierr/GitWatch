package com.senierr.repository.entity.dto

/**
 * 分类
 *
 * @author zhouchunjie
 * @date 2020/5/23 13:18
 */
data class Chapter(
    var courseId: Long = 0,
    var id: Long = 0,
    var name: String = "",
    var order: Int = 0,
    var parentChapterId: Long = 0,
    var userControlSetTop: Boolean = false,
    var visible: Int = 0
)