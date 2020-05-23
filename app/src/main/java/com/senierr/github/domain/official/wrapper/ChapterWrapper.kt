package com.senierr.github.domain.official.wrapper

import android.widget.TextView
import com.senierr.adapter.internal.ViewHolder
import com.senierr.adapter.internal.ViewHolderWrapper
import com.senierr.github.R
import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.WxChapter

/**
 * 公众号适配器
 *
 * @author zhouchunjie
 * @date 2020/5/10
 */
class ChapterWrapper : ViewHolderWrapper<WxChapter>(R.layout.item_official_chapter) {

    private var lastSelectedPosition: Int = -1
    private var currentSelectedPosition: Int = 0

    override fun onBindViewHolder(holder: ViewHolder, item: WxChapter) {
        val tvName = holder.findView<TextView>(R.id.tv_name)

        tvName?.text = item.name
        if (currentSelectedPosition == holder.layoutPosition) {
            tvName?.isSelected = true
            tvName?.setBackgroundResource(R.drawable.shape_shapter_bg)
        } else {
            tvName?.isSelected = false
            tvName?.background = null
        }
    }

    /**
     * 设置选中项
     */
    fun setSelected(position: Int) {
        lastSelectedPosition = currentSelectedPosition
        currentSelectedPosition = position
        multiTypeAdapter.notifyItemChanged(lastSelectedPosition)
        multiTypeAdapter.notifyItemChanged(currentSelectedPosition)
    }

    /**
     * 获取选中项
     */
    fun getSelected(): Int = currentSelectedPosition
}