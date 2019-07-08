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
    var avatarUrl: String = "",
    @JSONField(name = "bio")
    var bio: String = "",
    @JSONField(name = "blog")
    var blog: String = "",
    @JSONField(name = "collaborators")
    var collaborators: Int = 0,
    @JSONField(name = "company")
    var company: String = "",
    @JSONField(name = "created_at")
    var createdAt: String = "",
    @JSONField(name = "disk_usage")
    var diskUsage: Int = 0,
    @JSONField(name = "email")
    var email: String = "",
    @JSONField(name = "events_url")
    var eventsUrl: String = "",
    @JSONField(name = "followers")
    var followers: Int = 0,
    @JSONField(name = "followers_url")
    var followersUrl: String = "",
    @JSONField(name = "following")
    var following: Int = 0,
    @JSONField(name = "following_url")
    var followingUrl: String = "",
    @JSONField(name = "gists_url")
    var gistsUrl: String = "",
    @JSONField(name = "gravatar_id")
    var gravatarId: String = "",
    @JSONField(name = "hireable")
    var hireable: Any = Any(),
    @JSONField(name = "html_url")
    var htmlUrl: String = "",
    @JSONField(name = "id")
    var id: Int = 0,
    @JSONField(name = "location")
    var location: Any = Any(),
    @JSONField(name = "login")
    var login: String = "",
    @JSONField(name = "name")
    var name: String = "",
    @JSONField(name = "node_id")
    var nodeId: String = "",
    @JSONField(name = "organizations_url")
    var organizationsUrl: String = "",
    @JSONField(name = "owned_private_repos")
    var ownedPrivateRepos: Int = 0,
    @JSONField(name = "plan")
    var plan: Plan = Plan(),
    @JSONField(name = "private_gists")
    var privateGists: Int = 0,
    @JSONField(name = "public_gists")
    var publicGists: Int = 0,
    @JSONField(name = "public_repos")
    var publicRepos: Int = 0,
    @JSONField(name = "received_events_url")
    var receivedEventsUrl: String = "",
    @JSONField(name = "repos_url")
    var reposUrl: String = "",
    @JSONField(name = "site_admin")
    var siteAdmin: Boolean = false,
    @JSONField(name = "starred_url")
    var starredUrl: String = "",
    @JSONField(name = "subscriptions_url")
    var subscriptionsUrl: String = "",
    @JSONField(name = "total_private_repos")
    var totalPrivateRepos: Int = 0,
    @JSONField(name = "two_factor_authentication")
    var twoFactorAuthentication: Boolean = false,
    @JSONField(name = "type")
    var type: String = "",
    @JSONField(name = "updated_at")
    var updatedAt: String = "",
    @JSONField(name = "url")
    var url: String = ""
)