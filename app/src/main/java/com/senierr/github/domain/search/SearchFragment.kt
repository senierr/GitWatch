package com.senierr.github.domain.search

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.senierr.adapter.internal.MultiTypeAdapter
import com.senierr.adapter.support.bean.LoadMoreBean
import com.senierr.base.support.ui.BaseFragment
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.common.WebViewActivity
import com.senierr.github.domain.common.wrapper.LoadMoreWrapper
import com.senierr.github.domain.search.vm.SearchViewModel
import com.senierr.github.domain.search.wrapper.RepoWrapper
import com.senierr.repository.entity.dto.HttpException
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * 事件页面
 *
 * @author zhouchunjie
 * @date 2019/7/8 21:21
 */
class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private val multiTypeAdapter = MultiTypeAdapter()
    private val repoWrapper = RepoWrapper()
    private val loadMoreWrapper = LoadMoreWrapper()

    private lateinit var searchViewModel: SearchViewModel

    private var page = 1
    private val perPage = 20

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModel()
        msv_state?.showLoadingView()
        searchViewModel.searchRepos(page, perPage)
    }

    private fun initView() {
        srl_refresh?.setOnRefreshListener {
            page = 1
            searchViewModel.searchRepos(page, perPage)
        }
        rv_list?.layoutManager = LinearLayoutManager(context)
        // 列表
        repoWrapper.setOnItemClickListener { _, _, item ->
            context?.let { WebViewActivity.start(it, item.htmlUrl, item.humanName) }
        }
        multiTypeAdapter.register(repoWrapper)
        // 加载更多
        loadMoreWrapper.onLoadMoreListener = {
            searchViewModel.searchRepos(page, perPage)
        }
        multiTypeAdapter.register(loadMoreWrapper)
        rv_list?.adapter = multiTypeAdapter
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        searchViewModel.searchRepoSuccess.observe(this, Observer {
            srl_refresh?.isRefreshing = false
            if (page == 1) {
                if (it.isEmpty()) {
                    msv_state?.showEmptyView()
                } else {
                    msv_state?.showContentView()
                    multiTypeAdapter.data.clear()
                    multiTypeAdapter.data.addAll(it)
                    multiTypeAdapter.data.add(loadMoreWrapper.loadMoreBean)
                    multiTypeAdapter.notifyDataSetChanged()
                    page++
                }
            } else {
                if (it.isEmpty()) {
                    loadMoreWrapper.loadNoMore()
                } else {
                    val startPosition = multiTypeAdapter.data.size - 1
                    multiTypeAdapter.data.addAll(startPosition, it)
                    multiTypeAdapter.notifyItemRangeInserted(startPosition, it.size)
                    page++
                    loadMoreWrapper.loadCompleted()
                }
            }
        })
        searchViewModel.searchRepoFailure.observe(this, Observer {
            srl_refresh?.isRefreshing = false
            if (it is HttpException) {
                ToastUtil.showShort(context, it.message)
            }
            if (page == 1) {
                msv_state?.showErrorView()
            } else {
                loadMoreWrapper.loadFailure()
            }
        })
    }
}