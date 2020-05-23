package com.senierr.github.domain.official.wrapper

import android.content.Context
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.nex3z.flowlayout.FlowLayout
import com.senierr.adapter.internal.ViewHolder
import com.senierr.adapter.internal.ViewHolderWrapper
import com.senierr.base.support.utils.ScreenUtil
import com.senierr.github.R
import com.senierr.github.ext.translation
import com.senierr.github.utils.DateFormatUtil
import com.senierr.repository.entity.dto.Article

/**
 * 文章适配器
 *
 * @author zhouchunjie
 * @date 2020/5/10
 */
class ArticleWrapper : ViewHolderWrapper<Article>(R.layout.item_home_article) {

    override fun onBindViewHolder(holder: ViewHolder, item: Article) {
        val context = holder.itemView.context

        val tvTitle = holder.findView<TextView>(R.id.tv_title)
        val flTag = holder.findView<FlowLayout>(R.id.fl_tag)
        val tvCreator = holder.findView<TextView>(R.id.tv_creator)
        val tvPublishAt = holder.findView<TextView>(R.id.tv_publish_at)
        val ivFavorite = holder.findView<ImageView>(R.id.iv_favorite)

        tvTitle?.text = item.title.translation()
        flTag?.removeAllViews()
        if (item.type == Article.TYPE_TOP) {
            flTag?.addView(createWarnTagView(context, context.getString(R.string.tag_roof)))
        }
        if (item.fresh) {
            flTag?.addView(createWarnTagView(context, context.getString(R.string.tag_new)))
        }
        item.tags.forEach {
            flTag?.addView(createAccentTagView(context, it.name))
        }
        flTag?.addView(createAccentTagView(context, "${item.superChapterName}/${item.chapterName}"))
        tvCreator?.text = if (item.author.isNotBlank()) {
            context.getString(R.string.format_author, item.author)
        } else {
            context.getString(R.string.format_sharer, item.shareUser)
        }
        tvPublishAt?.text = DateFormatUtil.getNormalDate(context, item.publishTime)
        ivFavorite?.isSelected = item.collect
    }

    /**
     * 创建提醒标签
     */
    private fun createWarnTagView(context: Context, text: String): TextView {
        val tagView = TextView(context)
        tagView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        tagView.text = text
        tagView.textSize = 12F
        tagView.setTextColor(ContextCompat.getColor(context, R.color.app_warn))
        tagView.setBackgroundResource(R.drawable.shape_tag_warn)
        tagView.setPadding(
            ScreenUtil.dp2px(context, 4F),
            ScreenUtil.dp2px(context, 2F),
            ScreenUtil.dp2px(context, 4F),
            ScreenUtil.dp2px(context, 2F)
        )
        return tagView
    }

    /**
     * 创建普通标签
     */
    private fun createAccentTagView(context: Context, text: String): TextView {
        val tagView = TextView(context)
        tagView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        tagView.text = text
        tagView.textSize = 12F
        tagView.setTextColor(ContextCompat.getColor(context, R.color.app_accent))
        tagView.setBackgroundResource(R.drawable.shape_tag_accent)
        tagView.setPadding(
            ScreenUtil.dp2px(context, 4F),
            ScreenUtil.dp2px(context, 2F),
            ScreenUtil.dp2px(context, 4F),
            ScreenUtil.dp2px(context, 2F)
        )
        return tagView
    }
}