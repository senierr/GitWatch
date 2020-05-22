package com.senierr.repository.service.api

import com.senierr.repository.entity.dto.UserInfo

/**
 * 用户服务
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
    suspend fun login(username: String, password: String): UserInfo

    /**
     * 自动登录
     */
    suspend fun autoLogin(): UserInfo

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param repassword 确认密码
     */
    suspend fun register(username: String, password: String, repassword: String): UserInfo

    /**
     * 登出
     */
    suspend fun logout(): UserInfo
}