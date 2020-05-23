package com.senierr.github.domain.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.senierr.adapter.internal.MultiTypeAdapter
import com.senierr.base.support.ui.BaseFragment
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.common.WebViewActivity
import com.senierr.github.domain.common.wrapper.LoadMoreWrapper
import com.senierr.github.domain.home.vm.HomeViewModel
import com.senierr.github.domain.home.wrapper.ArticleWrapper
import com.senierr.github.domain.home.wrapper.BannerWrapper
import com.senierr.repository.entity.dto.Article
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * 事件页面
 *
 * @author zhouchunjie
 * @date 2019/7/8 21:21
 */
class HomeFragment : BaseFragment(R.layout.fragment_search) {

    private val multiTypeAdapter = MultiTypeAdapter()
    private val bannerWrapper = BannerWrapper()
    private val articleWrapper = ArticleWrapper()
    private val loadMoreWrapper = LoadMoreWrapper()

    private lateinit var searchViewModel: HomeViewModel

    private var page = 1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModel()
        msv_state?.showLoadingView()
        searchViewModel.refreshHome(page)
    }

    private fun initView() {
        srl_refresh?.setOnRefreshListener {
            page = 1
            searchViewModel.refreshHome(page)
        }
        rv_list?.layoutManager = LinearLayoutManager(context)
        // Banner
        multiTypeAdapter.register(bannerWrapper)
        // 列表
        articleWrapper.setOnItemClickListener { _, _, item ->
            context?.let { WebViewActivity.start(it, item.link, item.title) }
        }
        articleWrapper.setOnChildClickListener(R.id.iv_favorite) { _, _, _, item ->
            ToastUtil.showShort(context, "item.collect: ${item.collect}")
        }
        multiTypeAdapter.register(articleWrapper)
        // 加载更多
        loadMoreWrapper.onLoadMoreListener = {
            searchViewModel.loadMoreArticles(page)
        }
        multiTypeAdapter.register(loadMoreWrapper)
        rv_list?.adapter = multiTypeAdapter
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        searchViewModel.loadMoreArticleSuccess.observe(this, Observer {
            loadMoreArticles(it)
        })
        searchViewModel.loadMoreArticleFailure.observe(this, Observer {
            loadMoreWrapper.loadFailure()
        })

        searchViewModel.refreshHomeSuccess.observe(this, Observer {
            refreshHome(it)
        })
        searchViewModel.refreshHomeFailure.observe(this, Observer {
            msv_state?.showErrorView()
        })
    }

    /**
     * 刷新首页
     */
    private fun refreshHome(refreshResult: HomeViewModel.RefreshResult) {
        srl_refresh?.isRefreshing = false
        if (refreshResult.isEmpty()) {
            msv_state?.showEmptyView()
        } else {
            msv_state?.showContentView()
            multiTypeAdapter.data.clear()
            multiTypeAdapter.data.add(BannerWrapper.HomeBanner(refreshResult.banners))
            multiTypeAdapter.data.addAll(refreshResult.topArticles)
            multiTypeAdapter.data.addAll(refreshResult.articles)
            multiTypeAdapter.data.add(loadMoreWrapper.loadMoreBean)
            multiTypeAdapter.notifyDataSetChanged()
            page++
        }
    }

    /**
     * 加载更多文章
     */
    private fun loadMoreArticles(result: MutableList<Article>) {
        if (result.isEmpty()) {
            loadMoreWrapper.loadNoMore()
        } else {
            val startPosition = multiTypeAdapter.data.size - 1
            multiTypeAdapter.data.addAll(startPosition, result)
            multiTypeAdapter.notifyItemRangeInserted(startPosition, result.size)
            loadMoreWrapper.loadCompleted()
            page++
        }
    }
}