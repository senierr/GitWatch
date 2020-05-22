package com.senierr.github.domain.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senierr.repository.Repository
import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.Banner
import com.senierr.repository.service.api.IArticleService
import com.senierr.repository.service.api.IBannerService
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * 搜索
 *
 * @author zhouchunjie
 * @date 2019/7/9
 */
class HomeViewModel : ViewModel() {

    data class RefreshResult(
        val banners: MutableList<Banner>,
        val topArticles: MutableList<Article>,
        val articles: MutableList<Article>
    ) {
        fun isEmpty(): Boolean = banners.isEmpty() && topArticles.isEmpty() && articles.isEmpty()
    }

    val refreshHomeSuccess = MutableLiveData<RefreshResult>()
    val refreshHomeFailure = MutableLiveData<Exception>()

    val loadMoreArticleSuccess = MutableLiveData<MutableList<Article>>()
    val loadMoreArticleFailure = MutableLiveData<Exception>()

    private val articleService = Repository.getService<IArticleService>()
    private val bannerService = Repository.getService<IBannerService>()

    /**
     * 获取首页数据
     */
    fun refreshHome(page: Int) {
        viewModelScope.launch {
            // 轮播图
            val banners = try {
                bannerService.getHomeBanner()
            } catch (e: Exception) { null }
            // 置顶文章
            val topArticles = try {
                articleService.getTopArticles()
            } catch (e: Exception) { null }
            // 文章
            val articles = try {
                articleService.getArticles(page).datas
            } catch (e: Exception) { null }

            if (banners != null && topArticles != null && articles != null) {
                refreshHomeSuccess.value = RefreshResult(banners, topArticles, articles)
            } else {
                refreshHomeFailure.value = IOException()
            }
        }
    }

    /**
     * 加载更多文章
     */
    fun loadMoreArticles(page: Int) {
        viewModelScope.launch {
            try {
                val articles = articleService.getArticles(page).datas
                loadMoreArticleSuccess.value = articles
            } catch (e: Exception) {
                loadMoreArticleFailure.value = e
            }
        }
    }
}