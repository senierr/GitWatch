package com.senierr.github.domain.official

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.senierr.adapter.internal.MultiTypeAdapter
import com.senierr.base.support.ui.BaseFragment
import com.senierr.base.support.utils.KeyboardUtil
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.common.WebViewActivity
import com.senierr.github.domain.common.wrapper.LoadMoreWrapper
import com.senierr.github.domain.official.vm.OfficialViewModel
import com.senierr.github.domain.official.wrapper.ArticleWrapper
import com.senierr.github.domain.official.wrapper.ChapterWrapper
import com.senierr.github.ext.showContentView
import com.senierr.github.ext.showEmptyView
import com.senierr.github.ext.showLoadingView
import com.senierr.github.ext.showNetworkErrorView
import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.Chapter
import kotlinx.android.synthetic.main.fragment_official.*
import kotlinx.android.synthetic.main.fragment_official.msv_state
import kotlinx.android.synthetic.main.fragment_official.rv_list

/**
 * 公众号页面
 *
 * @author zhouchunjie
 * @date 2019/7/8 21:21
 */
class OfficialFragment : BaseFragment(R.layout.fragment_official) {

    private val chapterAdapter = MultiTypeAdapter()
    private val chapterWrapper = ChapterWrapper()

    private val articleAdapter = MultiTypeAdapter()
    private val articleWrapper = ArticleWrapper()
    private val loadMoreWrapper = LoadMoreWrapper()

    private lateinit var officialViewModel: OfficialViewModel

    // 当前选中的公众号
    private var currentChapter: Chapter? = null
    // 文章列表页索引
    private var pageIndex = 1
    // 是否搜索模式
    private var isSearchModel = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            initView(it)
            initViewModel()

            officialViewModel.getChapters()
        }
    }

    private fun initView(context: Context) {
        // 公众号列表
        rv_chapter?.layoutManager = LinearLayoutManager(context)
        chapterWrapper.setOnItemClickListener { _, position, item ->
            currentChapter = item
            chapterWrapper.setSelected(position)
            doRefresh(false)
        }
        chapterAdapter.register(chapterWrapper)
        rv_chapter?.adapter = chapterAdapter
        // 搜索
        et_search?.setOnEditorActionListener { _, _, _ ->
            doRefresh(true)
            return@setOnEditorActionListener true
        }
        // 文章列表
        rv_list?.layoutManager = LinearLayoutManager(context)
        // 文章
        articleWrapper.setOnItemClickListener { _, _, item ->
            WebViewActivity.start(context, item.link, item.title)
        }
        articleAdapter.register(articleWrapper)
        // 加载更多
        loadMoreWrapper.onLoadMoreListener = {
            doLoadMore()
        }
        articleAdapter.register(loadMoreWrapper)
        rv_list?.adapter = articleAdapter
    }

    private fun initViewModel() {
        officialViewModel = ViewModelProvider(this).get(OfficialViewModel::class.java)
        officialViewModel.getChaptersSuccess.observe(this, Observer {
            chapterAdapter.data.clear()
            chapterAdapter.data.addAll(it)
            chapterAdapter.notifyDataSetChanged()
            // 加载文章列表
            currentChapter = it.firstOrNull()
            doRefresh(false)
        })
        officialViewModel.getChaptersFailure.observe(this, Observer {
            ToastUtil.showShort(context, R.string.network_error)
        })

        officialViewModel.getArticlesSuccess.observe(this, Observer {
            if (pageIndex == 1) {
                renderRefreshResult(it.datas)
            } else {
                renderLoadMoreResult(it.datas)
            }
        })
        officialViewModel.getArticlesFailure.observe(this, Observer {
            if (pageIndex == 1) {
                msv_state?.showNetworkErrorView {
                    doRefresh(false)
                }
            } else {
                loadMoreWrapper.loadFailure()
            }
        })

        officialViewModel.searchByChapterIdSuccess.observe(this, Observer {
            if (pageIndex == 1) {
                renderRefreshResult(it.datas)
            } else {
                renderLoadMoreResult(it.datas)
            }
        })
        officialViewModel.searchByChapterIdFailure.observe(this, Observer {
            if (pageIndex == 1) {
                msv_state?.showNetworkErrorView {
                    doRefresh(true)
                }
            } else {
                loadMoreWrapper.loadFailure()
            }
        })
    }

    /**
     * 刷新文章列表
     *
     * @param isSearch 是否搜索模式
     */
    private fun doRefresh(isSearch: Boolean) {
        currentChapter?.let { chapter ->
            isSearchModel = isSearch
            pageIndex = 1
            msv_state?.showLoadingView()
            if (isSearch) {
                KeyboardUtil.hideSoftInput(activity)
                officialViewModel.searchByChapterId(chapter.id, pageIndex, et_search?.text.toString())
            } else {
                officialViewModel.getArticles(chapter.id, pageIndex)
            }
        }
    }

    /**
     * 加载更多文章
     */
    private fun doLoadMore() {
        currentChapter?.let { chapter ->
            if (isSearchModel) {
                officialViewModel.searchByChapterId(chapter.id, pageIndex, et_search?.text.toString())
            } else {
                officialViewModel.getArticles(chapter.id, pageIndex)
            }
        }
    }

    /**
     * 渲染刷新结果
     */
    private fun renderRefreshResult(result: MutableList<Article>) {
        if (result.isEmpty()) {
            msv_state?.showEmptyView()
        } else {
            msv_state?.showContentView()
            // 返回顶部
            val layoutManager = rv_list?.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(0, 0)

            articleAdapter.data.clear()
            articleAdapter.data.addAll(result)
            articleAdapter.data.add(loadMoreWrapper.loadMoreBean)
            articleAdapter.notifyDataSetChanged()
            pageIndex++
        }
    }

    /**
     * 渲染加载更多
     */
    private fun renderLoadMoreResult(result: MutableList<Article>) {
        if (result.isEmpty()) {
            loadMoreWrapper.loadNoMore()
        } else {
            val startPosition = articleAdapter.data.size - 1
            articleAdapter.data.addAll(startPosition, result)
            articleAdapter.notifyItemRangeInserted(startPosition, result.size)
            loadMoreWrapper.loadCompleted()
            pageIndex++
        }
    }
}