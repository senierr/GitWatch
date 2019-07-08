package com.senierr.repository.remote.entity

import com.alibaba.fastjson.annotation.JSONField

/**
 * 用户信息
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:34
 */
data class UserInfo(
    @JSONField(name = "avatar_url")
    var avatarUrl: String? = null,
    @JSONField(name = "bio")
    var bio: String? = null,
    @JSONField(name = "blog")
    var blog: String? = null,
    @JSONField(name = "company")
    var company: String? = null,
    @JSONField(name = "created_at")
    var createdAt: String? = null,
    @JSONField(name = "email")
    var email: String? = null,
    @JSONField(name = "followers")
    var followers: Int? = null,
    @JSONField(name = "following")
    var following: Int? = null,
    @JSONField(name = "gravatar_id")
    var gravatarId: String? = null,
    @JSONField(name = "hireable")
    var hireable: String? = null,
    @JSONField(name = "html_url")
    var htmlUrl: String? = null,
    @JSONField(name = "id")
    var id: Int? = null,
    @JSONField(name = "location")
    var location: String? = null,
    @JSONField(name = "login")
    var login: String? = null,
    @JSONField(name = "name")
    var name: String? = null,
    @JSONField(name = "node_id")
    var nodeId: String? = null,
    @JSONField(name = "public_gists")
    var publicGists: Int? = null,
    @JSONField(name = "public_repos")
    var publicRepos: Int? = null,
    @JSONField(name = "updated_at")
    var updatedAt: String? = null
)