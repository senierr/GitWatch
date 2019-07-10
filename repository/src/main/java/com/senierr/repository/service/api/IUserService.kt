package com.senierr.repository.service.api

import com.senierr.repository.remote.entity.HttpResponse
import com.senierr.repository.remote.entity.UserInfo
import io.reactivex.Observable

/**
 * 用户数据接口
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:30
 */
interface IUserService {

    /**
     * 登录
     */
    fun login(account: String, password: String): Observable<HttpResponse<UserInfo>>

    /**
     * 缓存当前用户
     */
    fun cacheCurrentUser(userInfo: UserInfo): Observable<Boolean>

    /**
     * 获取当前用户
     */
    fun getCurrentUser(): Observable<UserInfo>
}