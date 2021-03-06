package com.kotlin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.loading
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant.Companion.KEY_CATEGORY_ID
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.injection.component.DaggerCategroyComponet
import com.kotlin.goods.injection.module.CategoryModule
import com.kotlin.goods.presenter.CategroyPresenter
import com.kotlin.goods.presenter.view.CategroyView
import com.kotlin.goods.ui.activity.GoodsActivity
import com.kotlin.goods.ui.adapter.SecondCategoryAdapter
import com.kotlin.goods.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by  on 2018/7/27.YaoKai
 */
class CategoryFragment:BaseMvpFragment<CategroyPresenter>(),CategroyView {

    lateinit var mTopCategroyAdapter:TopCategoryAdapter
    lateinit var mSecondCategroyAdapter:SecondCategoryAdapter

    override fun injectComponent() {
        DaggerCategroyComponet.builder().activityComponent(activityComponent).categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        loadData()
    }

    private fun initView() {
        mTopCategroyAdapter=TopCategoryAdapter(context!!)
        mTopCategoryRv.layoutManager=LinearLayoutManager(context)
        mTopCategoryRv.adapter=mTopCategroyAdapter
        mTopCategroyAdapter.setOnItemClickListener(object:BaseRecyclerViewAdapter.OnItemClickListener<Category>{
            override fun onItemClick(item: Category, position: Int) {
                for (category in mTopCategroyAdapter.dataList){
                    category.isSelected= category.id==item.id
                }
                mTopCategroyAdapter.notifyDataSetChanged()
                loadData(item.id)
            }
        })

        mSecondCategroyAdapter=SecondCategoryAdapter(context!!)
        mSecondCategoryRv.layoutManager=GridLayoutManager(context!!,3)
        mSecondCategoryRv.adapter=mSecondCategroyAdapter
        mSecondCategroyAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {

                startActivity<GoodsActivity>(KEY_CATEGORY_ID to item.id)

            }
        })
    }

    private fun loadData(parentId:Int=0) {
        if(parentId!=0){
            mMultiStateView.loading()
        }
        mPresenter.getCategory(parentId)
    }

    override fun onGetCategoryResult(mutableList: MutableList<Category>?) {
        if (mutableList!=null&&mutableList.size>0){
            if (mutableList[0].parentId==0){
                mutableList[0].isSelected=true
                mTopCategroyAdapter.setData(mutableList)
                loadData(mutableList[0].id)
            }else{
                mSecondCategroyAdapter.setData(mutableList)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState=MultiStateView.VIEW_STATE_CONTENT
            }
        }else{
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState=MultiStateView.VIEW_STATE_EMPTY
        }
    }
}