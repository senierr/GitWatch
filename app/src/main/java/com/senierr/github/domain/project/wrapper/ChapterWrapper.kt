package com.senierr.github.domain.project.wrapper

import android.widget.TextView
import com.senierr.adapter.internal.ViewHolder
import com.senierr.adapter.internal.ViewHolderWrapper
import com.senierr.github.R
import com.senierr.repository.entity.dto.Chapter

/**
 * 分类适配器
 *
 * @author zhouchunjie
 * @date 2020/5/10
 */
class ChapterWrapper : ViewHolderWrapper<Chapter>(R.layout.item_project_chapter) {

    private var lastSelectedPosition: Int = -1
    private var currentSelectedPosition: Int = 0

    override fun onBindViewHolder(holder: ViewHolder, item: Chapter) {
        val tvName = holder.findView<TextView>(R.id.tv_name)

        tvName?.text = item.name
        tvName?.isSelected = currentSelectedPosition == holder.layoutPosition
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