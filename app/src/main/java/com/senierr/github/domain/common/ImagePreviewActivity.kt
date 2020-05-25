package com.senierr.github.domain.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bm.library.PhotoView
import com.senierr.adapter.internal.ViewHolder
import com.senierr.base.support.ui.BaseActivity
import com.senierr.github.R
import com.senierr.github.domain.common.vm.DownloadViewModel
import com.senierr.github.ext.show
import kotlinx.android.synthetic.main.activity_image_preview.*

/**
 * 图片预览页面
 *
 * @author zhouchunjie
 * @date 2019/5/30 10:03
 */
class ImagePreviewActivity : BaseActivity(R.layout.activity_image_preview) {

    companion object {
        private const val KEY_IMAGES = "images"

        fun start(context: Context, images: ArrayList<String>) {
            val intent = Intent(context, ImagePreviewActivity::class.java)
            intent.putStringArrayListExtra(KEY_IMAGES, images)
            context.startActivity(intent)
        }
    }

    private lateinit var downloadViewModel: DownloadViewModel

    private val images = mutableListOf<String>()
    private val imagePreviewAdapter = object : androidx.viewpager.widget.PagerAdapter() {
        override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

        override fun getCount(): Int = images.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val holder = ViewHolder.create(container, R.layout.item_image_preview)
            val pvPreview = holder.findView<PhotoView>(R.id.pv_preview)
            pvPreview?.setOnLongClickListener {
                downloadViewModel.download(images[position], "image")
                return@setOnLongClickListener true
            }

            pvPreview?.enable()
            pvPreview?.show(images[position])

            container.addView(holder.itemView)
            return holder.itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
        initView()
        downloadViewModel = ViewModelProvider(this).get(DownloadViewModel::class.java)
    }

    private fun initParams() {
        val temp = intent.getStringArrayListExtra(KEY_IMAGES)
        images.addAll(temp)
    }

    private fun initView() {
        vp_preview?.adapter = imagePreviewAdapter
    }

    private fun initViewModel() {
        
    }
}