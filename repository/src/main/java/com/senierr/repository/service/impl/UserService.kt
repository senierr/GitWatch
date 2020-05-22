package com.senierr.repository.service.impl

import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.UserInfo
import com.senierr.repository.remote.RemoteManager
import com.senierr.repository.remote.api.UserApi
import com.senierr.repository.service.api.IUserService
import com.senierr.repository.sp.SPKey
import com.senierr.repository.sp.SPManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @author zhouchunjie
 * @date 2020/5/15
 */
class UserService : IUserService {

    private val userApi by lazy { RemoteManager.getNormalHttp().create(UserApi::class.java) }
    private val spUtil by lazy { SPManager.getSP() }

    override suspend fun login(username: String, password: String): UserInfo {
        return withContext(Dispatchers.IO) {
            val response = userApi.login(username, password)
            if (!response.isSuccessful()) throw response.getException()
            spUtil.putString(SPKey.LOGIN_USERNAME, username)
            spUtil.putString(SPKey.LOGIN_PASSWORD, password)
            return@withContext response.data
        }
    }

    override suspend fun autoLogin(): UserInfo {
        return withContext(Dispatchers.IO) {
            val username = spUtil.getString(SPKey.LOGIN_USERNAME)
            val password = spUtil.getString(SPKey.LOGIN_PASSWORD)
            val response = userApi.login(username, password)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun register(username: String, password: String, repassword: String): UserInfo {
        return withContext(Dispatchers.IO) {
            val response = userApi.register(username, password, repassword)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun logout(): UserInfo {
        return withContext(Dispatchers.IO) {
            val response = userApi.logout()
            if (!response.isSuccessful()) throw response.getException()
            RemoteManager.getCookieStore().clear()
            return@withContext response.data
        }
    }
}