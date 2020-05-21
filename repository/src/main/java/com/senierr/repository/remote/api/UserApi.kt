package com.senierr.repository.remote.api

import com.senierr.repository.entity.dto.Token
import com.senierr.repository.entity.dto.UserInfo
import com.senierr.repository.remote.Constant
import retrofit2.http.*

/**
 * 用户模块API
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface UserApi {

    /**
     * 获取Token
     */
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getAccessToken(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String = "password",
        @Field("client_id") clientId: String = Constant.CLIENT_ID,
        @Field("client_secret") clientSecret: String = Constant.CLIENT_SECRET,
        @Field("scope") scope: String = "projects user_info issues notes"
    ): Token

    /**
     * 刷新Token
     */
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun refreshAccessToken(
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String = "refresh_token"
    ): Token

    /**
     * 获取授权用户的资料
     */
    @GET("api/v5/user")
    suspend fun getUserInfo(@Query("access_token") accessToken: String): UserInfo

    /**
     * 更新授权用户的资料
     *
     * @param name 昵称
     * @param blog 博客
     * @param weibo 微博
     * @param bio 自我介绍
     */
    @PATCH("api/v5/user")
    suspend fun updateUserInfo(
        @Query("access_token") accessToken: String,
        @Query("name") name: String? = null,
        @Query("bio") bio: String? = null,
        @Query("blog") blog: String? = null,
        @Query("weibo") weibo: String? = null
    ): UserInfo

    /**
     * 列出授权用户的关注者
     *
     * @param page 当前的页码
     * @param perPage 每页的数量，最大为 100
     */
    @GET("api/v5/user/followers")
    suspend fun getFollowers(
        @Query("access_token") accessToken: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: String
    ): MutableList<UserInfo>

    /**
     * 列出授权用户正关注的用户
     *
     * @param page 当前的页码
     * @param perPage 每页的数量，最大为 100
     */
    @GET("api/v5/user/followers")
    suspend fun getFollowing(
        @Query("access_token") accessToken: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: String
    ): MutableList<UserInfo>
}