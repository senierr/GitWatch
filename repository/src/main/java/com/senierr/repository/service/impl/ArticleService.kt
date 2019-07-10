package com.senierr.repository.service.impl

import com.senierr.repository.Repository
import com.senierr.repository.ext.asAsync
import com.senierr.repository.remote.RemoteApi
import com.senierr.repository.remote.entity.Article
import com.senierr.repository.remote.entity.Banner
import com.senierr.repository.remote.entity.HttpResponse
import com.senierr.repository.remote.entity.Page
import com.senierr.repository.service.api.IArticleService
import io.reactivex.Observable

/**
 * 文章数据接口实现
 *
 * @author zhouchunjie
 * @date 2019/7/10 19:43
 */
class ArticleService : IArticleService {

    override fun getList(pageIndex: Int): Observable<HttpResponse<Page<Article>>> {
        return Repository.rxHttp.get("${RemoteApi.ARTICLE_LIST}/$pageIndex/json")
            .asAsync<HttpResponse<Page<Article>>>(
                arrayOf(Page::class.java, Article::class.java)
            )
    }

    override fun getHomeBanner(): Observable<HttpResponse<MutableList<Banner>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}