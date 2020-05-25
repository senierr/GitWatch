package com.senierr.github.domain.project.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senierr.repository.Repository
import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.Chapter
import com.senierr.repository.entity.dto.PageResult
import com.senierr.repository.service.api.IProjectService
import kotlinx.coroutines.launch

/**
 * 项目
 *
 * @author zhouchunjie
 * @date 2019/7/9
 */
class ProjectViewModel : ViewModel() {

    val getChaptersSuccess = MutableLiveData<MutableList<Chapter>>()
    val getChaptersFailure = MutableLiveData<Exception>()

    val getArticlesSuccess = MutableLiveData<PageResult<Article>>()
    val getArticlesFailure = MutableLiveData<Exception>()

    private val projectService = Repository.getService<IProjectService>()

    /**
     * 获取分类列表
     */
    fun getChapters() {
        viewModelScope.launch {
            try {
                val response = projectService.getChapters()
                getChaptersSuccess.value = response
            } catch (e: Exception) {
                getChaptersFailure.value = e
            }
        }
    }

    /**
     * 获取某分类下项目列表数据
     */
    fun getArticles(pageIndex: Int, cid: Long) {
        viewModelScope.launch {
            try {
                val response = projectService.getArticles(pageIndex, cid)
                getArticlesSuccess.value = response
            } catch (e: Exception) {
                getArticlesFailure.value = e
            }
        }
    }
}