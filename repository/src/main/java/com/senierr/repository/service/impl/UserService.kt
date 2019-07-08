package com.senierr.repository.service.impl

import android.util.Base64
import com.senierr.repository.Repository
import com.senierr.repository.db.entity.DBCache
import com.senierr.repository.remote.entity.UserInfo
import com.senierr.repository.remote.GithubApi
import com.senierr.repository.remote.GithubArrayConverter
import com.senierr.repository.remote.GithubObjectConverter
import com.senierr.repository.remote.entity.Event
import com.senierr.repository.service.api.IUserService
import io.reactivex.Observable

/**
 * Github数据接口实现
 *
 * @author zhouchunjie
 * @date 2019/7/5 20:19
 */
class UserService : IUserService {

    override fun login(account: String, password: String): Observable<UserInfo> {
        val authString = "$account:$password"
        val authorization = "Basic ${Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)}"
        return Repository.rxHttp.get("${GithubApi.USER}$account")
            .addHeader("Authorization", authorization)
            .toResultObservable(GithubObjectConverter(UserInfo::class.java))
            .doAfterNext {
                // 缓存用户信息
                val currentAccount = DBCache("current_account", it.login)
                val currentAuthorization =
                    DBCache("current_authorization", authorization)
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

    override fun getReceivedEvents(
        account: String,
        authorization: String,
        pageIndex: Int,
        pageSize: Int
    ): Observable<MutableList<Event>> {
        return Repository.rxHttp.get("${GithubApi.USER}$account/received_events")
            .addHeader("Authorization", authorization)
            .toResultObservable(GithubArrayConverter(Event::class.java))
    }
}