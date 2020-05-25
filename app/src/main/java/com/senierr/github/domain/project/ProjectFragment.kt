package com.senierr.github.domain.project

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.senierr.adapter.internal.MultiTypeAdapter
import com.senierr.base.support.ui.BaseFragment
import com.senierr.base.support.ui.recyclerview.GridItemDecoration
import com.senierr.base.support.ui.recyclerview.LinearItemDecoration
import com.senierr.base.support.utils.ScreenUtil
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.common.ImagePreviewActivity
import com.senierr.github.domain.common.WebViewActivity
import com.senierr.github.domain.common.wrapper.LoadMoreWrapper
import com.senierr.github.domain.project.vm.ProjectViewModel
import com.senierr.github.domain.project.wrapper.ArticleWrapper
import com.senierr.github.domain.project.wrapper.ChapterWrapper
import com.senierr.github.ext.showContentView
import com.senierr.github.ext.showEmptyView
import com.senierr.github.ext.showLoadingView
import com.senierr.github.ext.showNetworkErrorView
import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.Chapter
import kotlinx.android.synthetic.main.fragment_official.*
import kotlinx.android.synthetic.main.fragment_official.msv_state
import kotlinx.android.synthetic.main.fragment_official.rv_chapter
import kotlinx.android.synthetic.main.fragment_official.rv_list
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * 项目页面
 *
 * @author zhouchunjie
 * @date 2020/5/25
 */
class ProjectFragment : BaseFragment(R.layout.fragment_project) {

    private val chapterAdapter = MultiTypeAdapter()
    private val chapterWrapper = ChapterWrapper()

    private val articleAdapter = MultiTypeAdapter()
    private val articleWrapper = ArticleWrapper()
    private val loadMoreWrapper = LoadMoreWrapper()

    private lateinit var projectViewModel: ProjectViewModel

    // 当前选中的分类
    private var currentChapter: Chapter? = null
    // 文章列表页索引
    private var pageIndex = 1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            initView(it)
            initViewModel()

            projectViewModel.getChapters()
        }
    }

    private fun initView(context: Context) {
        // 分类列表
        rv_chapter?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        chapterWrapper.setOnItemClickListener { _, position, item ->
            currentChapter = item
            chapterWrapper.setSelected(position)
            doRefresh()
        }
        chapterAdapter.register(chapterWrapper)
        rv_chapter?.adapter = chapterAdapter
        // 文章列表
        rv_list?.layoutManager = GridLayoutManager(context, 2)
        rv_list?.addItemDecoration(GridItemDecoration(ScreenUtil.dp2px(context, 4F), true))
        // 文章
        articleWrapper.setOnChildClickListener(R.id.iv_icon) { _,_, _, item ->
            ImagePreviewActivity.start(context, arrayListOf(item.envelopePic))
        }
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
        projectViewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
        projectViewModel.getChaptersSuccess.observe(this, Observer {
            chapterAdapter.data.clear()
            chapterAdapter.data.addAll(it)
            chapterAdapter.notifyDataSetChanged()
            // 加载文章列表
            currentChapter = it.firstOrNull()
            doRefresh()
        })
        projectViewModel.getChaptersFailure.observe(this, Observer {
            ToastUtil.showShort(context, R.string.network_error)
        })

        projectViewModel.getArticlesSuccess.observe(this, Observer {
            if (pageIndex == 1) {
                renderRefreshResult(it.datas)
            } else {
                renderLoadMoreResult(it.datas)
            }
        })
        projectViewModel.getArticlesFailure.observe(this, Observer {
            if (pageIndex == 1) {
                msv_state?.showNetworkErrorView { doRefresh() }
            } else {
                loadMoreWrapper.loadFailure()
            }
        })
    }

    /**
     * 刷新文章列表
     */
    private fun doRefresh() {
        currentChapter?.let { chapter ->
            pageIndex = 1
            msv_state?.showLoadingView()
            projectViewModel.getArticles(pageIndex, chapter.id)
        }
    }

    /**
     * 加载更多文章
     */
    private fun doLoadMore() {
        currentChapter?.let { chapter ->
            projectViewModel.getArticles(pageIndex, chapter.id)
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