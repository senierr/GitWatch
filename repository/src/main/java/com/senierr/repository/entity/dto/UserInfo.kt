package com.senierr.repository.entity.dto

import com.google.gson.annotations.SerializedName

/**
 * 用户
 */
data class UserInfo(
    val id: Long = 0,
    val url: String = "",
    val login: String = "",
    val name: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    val bio: String = "",
    val blog: String = "",
    val email: String = "",
    val stared: Int = 0,
    val watched: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = ""
)