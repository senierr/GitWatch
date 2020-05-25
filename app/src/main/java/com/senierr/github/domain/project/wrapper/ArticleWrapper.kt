package com.senierr.github.domain.project.wrapper

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.senierr.adapter.internal.ViewHolder
import com.senierr.adapter.internal.ViewHolderWrapper
import com.senierr.github.R
import com.senierr.github.ext.translation
import com.senierr.repository.entity.dto.Article

/**
 * 文章适配器
 *
 * @author zhouchunjie
 * @date 2020/5/10
 */
class ArticleWrapper : ViewHolderWrapper<Article>(R.layout.item_project_article) {

    override fun onBindViewHolder(holder: ViewHolder, item: Article) {
        val ivIcon = holder.findView<ImageView>(R.id.iv_icon)
        val tvTitle = holder.findView<TextView>(R.id.tv_title)
        val tvDesc = holder.findView<TextView>(R.id.tv_desc)
        val tvCreator = holder.findView<TextView>(R.id.tv_creator)
        val ivFavorite = holder.findView<ImageView>(R.id.iv_favorite)

        ivIcon?.let {
            Glide.with(it).load(item.envelopePic).into(it)
        }
        tvTitle?.text = item.title.translation()
        tvDesc?.text = item.desc.translation()
        tvCreator?.text = item.author
        ivFavorite?.isSelected = item.collect
    }
}