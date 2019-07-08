package com.senierr.repository.remote.entity

import com.alibaba.fastjson.annotation.JSONField

/**
 *
 * @author zhouchunjie
 * @date 2019/7/8
 */
data class Event(
    @JSONField(name = "public")
    var publicity: Boolean? = null,
    @JSONField(name = "actor")
    var actor: Actor? = null,
    @JSONField(name = "created_at")
    var createdAt: String? = null,
    @JSONField(name = "id")
    var id: String? = null,
    @JSONField(name = "repo")
    var repo: Repo? = null,
    @JSONField(name = "type")
    var type: String? = null
)

data class Actor(
    @JSONField(name = "avatar_url")
    var avatarUrl: String? = null,
    @JSONField(name = "display_login")
    var displayLogin: String? = null,
    @JSONField(name = "gravatar_id")
    var gravatarId: String? = null,
    @JSONField(name = "id")
    var id: Int? = null,
    @JSONField(name = "login")
    var login: String? = null,
    @JSONField(name = "url")
    var url: String? = null
)

data class Repo(
    @JSONField(name = "id")
    var id: Int? = null,
    @JSONField(name = "name")
    var name: String? = null,
    @JSONField(name = "url")
    var url: String? = null
)