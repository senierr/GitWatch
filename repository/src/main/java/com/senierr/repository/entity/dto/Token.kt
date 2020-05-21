package com.senierr.repository.entity.dto

import com.google.gson.annotations.SerializedName

/**
 * 授权实体
 */
data class Token(
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("created_at")
    val createdAt: Long = 0,
    @SerializedName("expires_in")
    val expiresIn: Long = 0,
    @SerializedName("refresh_token")
    val refreshToken: String = "",
    val scope: String = "",
    @SerializedName("token_type")
    val tokenType: String = ""
)