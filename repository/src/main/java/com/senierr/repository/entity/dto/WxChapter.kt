package com.senierr.repository.entity.dto

/**
 * 公众号分类
 *
 * @author zhouchunjie
 * @date 2020/5/23 13:18
 */
data class WxChapter(
    var courseId: Long = 0,
    var id: Long = 0,
    var name: String = "",
    var order: Int = 0,
    var parentChapterId: Long = 0,
    var userControlSetTop: Boolean = false,
    var visible: Int = 0
)