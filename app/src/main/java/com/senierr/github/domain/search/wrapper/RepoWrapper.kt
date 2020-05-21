package com.senierr.github.domain.search.wrapper

import android.widget.TextView
import com.senierr.adapter.internal.ViewHolder
import com.senierr.adapter.internal.ViewHolderWrapper
import com.senierr.github.R

/**
 * 仓库适配器
 *
 * @author zhouchunjie
 * @date 2020/5/10
 */
class RepoWrapper : ViewHolderWrapper<com.senierr.repository.entity.dto.Repo>(R.layout.item_repo) {

    override fun onBindViewHolder(holder: ViewHolder, item: com.senierr.repository.entity.dto.Repo) {
        val tvName = holder.findView<TextView>(R.id.tv_name)
        val tvCreator = holder.findView<TextView>(R.id.tv_creator)
        val tvDesc = holder.findView<TextView>(R.id.tv_desc)
        val tvLanguage = holder.findView<TextView>(R.id.tv_language)
        val tvWatch = holder.findView<TextView>(R.id.tv_watch)
        val tvStar = holder.findView<TextView>(R.id.tv_star)
        val tvFork = holder.findView<TextView>(R.id.tv_fork)
        val tvUpdateAt = holder.findView<TextView>(R.id.tv_update_at)

        tvName?.text = item.humanName
        tvCreator?.text = item.projectCreator
        tvDesc?.text = item.description
        tvLanguage?.text = item.language
        tvWatch?.text = item.watchersCount.toString()
        tvStar?.text = item.stargazersCount.toString()
        tvFork?.text = item.forksCount.toString()
        tvUpdateAt?.text = item.updatedAt
    }
}