package com.kotlin.base.widgets

import android.content.Context
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * Created by  on 2018/7/26.YaoKai
 */
class BannerImageLoader: ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context,path.toString(),imageView)
    }
}