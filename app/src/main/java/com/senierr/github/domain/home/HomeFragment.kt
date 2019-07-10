package com.senierr.github.domain.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senierr.adapter.internal.MultiTypeAdapter
import com.senierr.adapter.internal.ViewHolder
import com.senierr.adapter.internal.ViewHolderWrapper
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.base.support.ui.BaseFragment
import com.senierr.base.support.utils.LogUtil
import com.senierr.github.R
import com.senierr.repository.Repository
import com.senierr.repository.remote.entity.Article
import com.senierr.repository.service.api.IArticleService
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 事件页面
 *
 * @author zhouchunjie
 * @date 2019/7/8 21:21
 */
class HomeFragment : BaseFragment() {

    private var pageIndex = 1

    private val multiTypeAdapter = MultiTypeAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onLazyCreated() {
        super.onLazyCreated()
        initView()
    }

    private fun initView() {
        val viewHolder = object : ViewHolderWrapper<Article>() {
            override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
                return ViewHolder.create(parent, R.layout.item_home_article)
            }

            override fun onBindViewHolder(holder: ViewHolder, item: Article) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        rv_home?.layoutManager = LinearLayoutManager(context)


        Repository.getService<IArticleService>().getList(0)
            .subscribeOnIO()
            .observeOnMain()
            .subscribe{
                LogUtil.logE(it?.data?.datas?.get(0)?.title ?: "null")
            }
            .bindToLifecycle()
    }


}