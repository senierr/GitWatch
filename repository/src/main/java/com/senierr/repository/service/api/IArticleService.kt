package com.senierr.repository.service.api

import com.senierr.repository.remote.entity.*
import io.reactivex.Observable

/**
 * 文章数据接口
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:30
 */
interface IArticleService {

    /**
     * 获取列表
     *
     * @param pageIndex 页码，从0开始
     */
    fun getList(pageIndex: Int): Observable<HttpResponse<Page<Article>>>

    /**
     * 获取首页Banner
     */
    fun getHomeBanner(): Observable<HttpResponse<MutableList<Banner>>>
}