package com.senierr.github.domain.search.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senierr.repository.Repository
import com.senierr.repository.entity.dto.Repo
import com.senierr.repository.service.api.IRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 搜索
 *
 * @author zhouchunjie
 * @date 2019/7/9
 */
class SearchViewModel : ViewModel() {

    val searchRepoSuccess = MutableLiveData<MutableList<Repo>>()
    val searchRepoFailure = MutableLiveData<Exception>()

    private val repositoryService = Repository.getService<IRepositoryService>()

    /**
     * 搜索仓库
     */
    fun searchRepos(page: Int, perPage: Int) {
        viewModelScope.launch {
            try {
                val repos = repositoryService.searchRepositories("android", page, perPage)
                searchRepoSuccess.value = repos
            } catch (e: Exception) {
                searchRepoFailure.value = e
            }
        }
    }
}