package com.senierr.repository.service.impl

import android.util.Base64
import com.senierr.repository.Repository
import com.senierr.repository.entity.DBCache
import com.senierr.repository.entity.UserInfo
import com.senierr.repository.remote.GithubApi
import com.senierr.repository.remote.GithubConverter
import com.senierr.repository.service.api.IUserService
import io.reactivex.Observable

/**
 * Github数据接口实现
 *
 * @author zhouchunjie
 * @date 2019/7/5 20:19
 */
class UserService : IUserService {

    override fun login(username: String, password: String): Observable<UserInfo> {
        val authString = "$username:$password"
        val authorization = "Basic ${Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)}"
        return Repository.rxHttp.get("${GithubApi.USER}$username")
            .addHeader("Authorization", authorization)
            .toResultObservable(GithubConverter(UserInfo::class.java))
            .doAfterNext {
                // 缓存用户信息
                val currentAccount = DBCache("current_account", it.login)
                val currentAuthorization = DBCache("current_authorization", authorization)
                Repository.database.getDBCacheDao().insertOrReplace(currentAccount)
                Repository.database.getDBCacheDao().insertOrReplace(currentAuthorization)
            }
    }

    override fun getCurrentAccount(): Observable<String> {
        return Observable.fromCallable {
            return@fromCallable Repository.database.getDBCacheDao().get("current_account").value
        }
    }

    override fun getAuthorization(): Observable<String> {
        return Observable.fromCallable {
            return@fromCallable Repository.database.getDBCacheDao().get("current_authorization").value
        }
    }
}