package com.senierr.repository.service

import com.senierr.repository.entity.UserInfo
import io.reactivex.Observable

/**
 * Github数据接口
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:30
 */
interface GithubService {

    /**
     * Basic authentication登录
     */
    fun login(username: String, password: String): Observable<UserInfo>
}