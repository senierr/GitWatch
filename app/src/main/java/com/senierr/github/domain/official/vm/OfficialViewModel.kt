package com.senierr.github.domain.official.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senierr.repository.Repository
import com.senierr.repository.entity.dto.*
import com.senierr.repository.service.api.IUserService
import com.senierr.repository.service.api.IWxArticleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 公众号
 *
 * @author zhouchunjie
 * @date 2019/7/9
 */
class OfficialViewModel : ViewModel() {

    val getChaptersSuccess = MutableLiveData<MutableList<WxChapter>>()
    val getChaptersFailure = MutableLiveData<Exception>()

    val getArticlesSuccess = MutableLiveData<PageResult<Article>>()
    val getArticlesFailure = MutableLiveData<Exception>()

    val searchByChapterIdSuccess = MutableLiveData<PageResult<Article>>()
    val searchByChapterIdFailure = MutableLiveData<Exception>()

    private val wxArticleService = Repository.getService<IWxArticleService>()

    /**
     * 获取公众号列表
     */
    fun getChapters() {
        viewModelScope.launch {
            try {
                val response = wxArticleService.getChapters()
                getChaptersSuccess.value = response
            } catch (e: Exception) {
                getChaptersFailure.value = e
            }
        }
    }

    /**
     * 获取公众号历史文章
     */
    fun getArticles(id: Long, pageIndex: Int) {
        viewModelScope.launch {
            try {
                val response = wxArticleService.getArticles(id, pageIndex)
                getArticlesSuccess.value = response
            } catch (e: Exception) {
                getArticlesFailure.value = e
            }
        }
    }

    /**
     * 搜索公众号下的文章
     */
    fun searchByChapterId(id: Long, pageIndex: Int, keyword: String) {
        viewModelScope.launch {
            try {
                val response = wxArticleService.searchByChapterId(id, pageIndex, keyword)
                searchByChapterIdSuccess.value = response
            } catch (e: Exception) {
                searchByChapterIdFailure.value = e
            }
        }
    }
}