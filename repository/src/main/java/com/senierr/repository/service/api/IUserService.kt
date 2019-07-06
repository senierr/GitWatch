package com.senierr.repository.service.api

import com.senierr.repository.entity.UserInfo
import io.reactivex.Observable

/**
 * Github数据接口
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:30
 */
interface IUserService {

    /**
     * Basic authentication登录
     * 登录完成后，会自动缓存用户信息
     */
    fun login(username: String, password: String): Observable<UserInfo>

    /**
     * 获取当前用户
     */
    fun getCurrentAccount(): Observable<String>

    /**
     * 获取用户信息
     */
    fun getAuthorization(): Observable<String>
}