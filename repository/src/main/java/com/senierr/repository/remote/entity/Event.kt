package com.senierr.repository.remote.entity

import com.alibaba.fastjson.annotation.JSONField

/**
 *
 * @author zhouchunjie
 * @date 2019/7/8
 */
data class Event(
    @JSONField(name = "public")
    val publicity: Boolean = false,
    val actor: Actor = Actor(),
    @JSONField(name = "created_at")
    val createdAt: String = "",
    val id: String = "",
    val repo: Repo = Repo(),
    val type: String = ""
)

data class Actor(
    @JSONField(name = "avatar_url")
    val avatarUrl: String = "",
    @JSONField(name = "display_login")
    val displayLogin: String = "",
    @JSONField(name = "gravatar_id")
    val gravatarId: String = "",
    val id: Int = 0,
    val login: String = "",
    val url: String = ""
)

data class Repo(
    val id: Int = 0,
    val name: String = "",
    val url: String = ""
)