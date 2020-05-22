package com.senierr.github.domain.home.wrapper

import android.widget.TextView
import com.senierr.adapter.internal.ViewHolder
import com.senierr.adapter.internal.ViewHolderWrapper
import com.senierr.github.R
import com.senierr.repository.entity.dto.Article

/**
 * 首页文章适配器
 *
 * @author zhouchunjie
 * @date 2020/5/10
 */
class ArticleWrapper : ViewHolderWrapper<Article>(R.layout.item_home_article) {

    override fun onBindViewHolder(holder: ViewHolder, item: Article) {
        val tvName = holder.findView<TextView>(R.id.tv_name)
        val tvCreator = holder.findView<TextView>(R.id.tv_creator)
        val tvDesc = holder.findView<TextView>(R.id.tv_desc)
        val tvLanguage = holder.findView<TextView>(R.id.tv_language)
        val tvWatch = holder.findView<TextView>(R.id.tv_watch)
        val tvStar = holder.findView<TextView>(R.id.tv_star)
        val tvFork = holder.findView<TextView>(R.id.tv_fork)
        val tvUpdateAt = holder.findView<TextView>(R.id.tv_update_at)

        tvName?.text = item.title
        tvCreator?.text = item.author
        tvDesc?.text = item.desc
        tvUpdateAt?.text = item.niceDate
    }
}