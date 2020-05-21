package com.senierr.repository.service.api

import com.senierr.repository.entity.dto.Token
import com.senierr.repository.entity.dto.UserInfo

/**
 * 用户数据服务
 *
 * @author zhouchunjie
 * @date 2020/5/15
 */
interface IUserService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    suspend fun login(username: String, password: String): Token

    /**
     * 自动登录
     */
    suspend fun autoLogin(): Token

    /**
     * 获取授权用户的资料
     */
    suspend fun getUserInfo(): UserInfo

    /**
     * 更新授权用户的资料
     *
     * @param name 昵称
     * @param bio 自我介绍
     * @param blog 博客
     */
    suspend fun updateUserInfo(name: String, bio: String?, blog: String?): UserInfo
}