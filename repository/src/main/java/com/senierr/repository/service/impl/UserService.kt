package com.senierr.repository.service.impl

import com.senierr.repository.entity.dto.Token
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

    override suspend fun login(username: String, password: String): Token {
        return withContext(Dispatchers.IO) {
            val token = userApi.getAccessToken(username, password)
            spUtil.putString(SPKey.USER_LOGIN_TOKEN, token.accessToken)
            spUtil.putString(SPKey.USER_LOGIN_REFRESH_TOKEN, token.refreshToken)
            return@withContext token
        }
    }

    override suspend fun autoLogin(): Token {
        return withContext(Dispatchers.IO) {
            val accessToken = spUtil.getString(SPKey.USER_LOGIN_TOKEN)
            val refreshToken = spUtil.getString(SPKey.USER_LOGIN_REFRESH_TOKEN)
            if (accessToken.isNotBlank() && refreshToken.isNotBlank()) {
                val token = userApi.refreshAccessToken(refreshToken)
                spUtil.putString(SPKey.USER_LOGIN_TOKEN, token.accessToken)
                spUtil.putString(SPKey.USER_LOGIN_REFRESH_TOKEN, token.refreshToken)
                return@withContext token
            } else {
                throw IllegalArgumentException("access_token or refresh_token is null.")
            }
        }
    }

    override suspend fun getUserInfo(): UserInfo {
        return withContext(Dispatchers.IO) {
            val accessToken = spUtil.getString(SPKey.USER_LOGIN_TOKEN)
            userApi.getUserInfo(accessToken)
        }
    }

    override suspend fun updateUserInfo(name: String, bio: String?, blog: String?): UserInfo {
        return withContext(Dispatchers.IO) {
            val accessToken = spUtil.getString(SPKey.USER_LOGIN_TOKEN)
            return@withContext userApi.updateUserInfo(accessToken, name, bio, blog)
        }
    }
}