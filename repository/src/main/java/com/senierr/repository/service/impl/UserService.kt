package com.senierr.repository.service.impl

import com.google.gson.Gson
import com.senierr.repository.Repository
import com.senierr.repository.db.entity.DBCache
import com.senierr.repository.ext.asAsync
import com.senierr.repository.remote.RemoteApi
import com.senierr.repository.remote.entity.HttpResponse
import com.senierr.repository.remote.entity.UserInfo
import com.senierr.repository.service.api.IUserService
import io.reactivex.Observable

/**
 * 用户数据接口实现
 *
 * @author zhouchunjie
 * @date 2019/7/5 20:19
 */
class UserService : IUserService {

    override fun login(account: String, password: String): Observable<HttpResponse<UserInfo>> {
        return Repository.rxHttp.post(RemoteApi.USER_LOGIN)
            .addRequestParam("username", account)
            .addRequestParam("password", password)
            .asAsync<HttpResponse<UserInfo>>(arrayOf(UserInfo::class.java))
            .doOnNext {
                val dbCache = DBCache("current_user", Gson().toJson(it))
                Repository.database.getDBCacheDao().insertOrReplace(dbCache)
            }
    }

    override fun cacheCurrentUser(userInfo: UserInfo): Observable<Boolean> {
        return Observable.fromCallable {
            val dbCache = DBCache("current_user", Gson().toJson(userInfo))
            Repository.database.getDBCacheDao().insertOrReplace(dbCache)
            return@fromCallable true
        }
    }

    override fun getCurrentUser(): Observable<UserInfo> {
        return Observable.fromCallable {
            val dbCache = Repository.database.getDBCacheDao().get("current_user")
            return@fromCallable Gson().fromJson(dbCache.value, UserInfo::class.java)
        }
    }
}