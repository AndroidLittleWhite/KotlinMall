package com.kotlin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ext.loadUrl
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goods.R
import com.kotlin.goods.data.protocol.Category
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/*
    首页折扣区域Adapter
 */
class SecondCategoryAdapter(context: Context): BaseRecyclerViewAdapter<Category, SecondCategoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_second_category_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val category = dataList[position]

        holder.itemView.mCategoryIconIv.loadUrl(category.categoryIcon)
        //商品名称
        holder.itemView.mSecondCategoryNameTv.text = category.categoryName
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}