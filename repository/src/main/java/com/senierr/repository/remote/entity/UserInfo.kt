package com.senierr.repository.remote.entity

/**
 * 用户信息
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:34
 */
data class UserInfo(
    var admin: Boolean? = null,
    var chapterTops: List<String>? = null,
    var collectIds: List<String>? = null,
    var email: String? = null,
    var icon: String? = null,
    var id: Int? = null,
    var nickname: String? = null,
    var password: String? = null,
    var token: String? = null,
    var type: Int? = null,
    var username: String? = null
)