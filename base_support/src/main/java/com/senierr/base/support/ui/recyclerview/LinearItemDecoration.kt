package com.senierr.base.support.ui.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * 线性布局模式分割线
 *
 * @author zhouchunjie
 * @date 2019/8/8
 */
class LinearItemDecoration(
    private val dividerSize: Int = 1,
    dividerColor: Int = Color.TRANSPARENT,
    private val leftMargin: Int = 0,
    private val rightMargin: Int = 0
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.isAntiAlias = true
        paint.color = dividerColor
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = dividerSize
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val dividerTop = view.bottom
            val dividerLeft = parent.paddingLeft + leftMargin
            val dividerBottom = view.bottom + dividerSize
            val dividerRight = parent.width - parent.paddingRight - rightMargin
            c.drawRect(dividerLeft.toFloat(), dividerTop.toFloat(), dividerRight.toFloat(), dividerBottom.toFloat(), paint)
        }
    }
}