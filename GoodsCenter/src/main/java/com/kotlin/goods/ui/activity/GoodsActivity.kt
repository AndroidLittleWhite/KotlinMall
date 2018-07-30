package com.kotlin.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.loading
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.common.GoodsConstant.Companion.KEY_GOODS_KEYWORD
import com.kotlin.goods.common.GoodsConstant.Companion.KEY_SEARCH_GOODS_TYPE
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.injection.component.DaggerGoodsComponet
import com.kotlin.goods.injection.module.GoodsModule
import com.kotlin.goods.presenter.GoodsListPresenter
import com.kotlin.goods.presenter.view.GoodsListView
import com.kotlin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*

/**
 * Created by  on 2018/7/27.YaoKai
 */
class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private var mCurrnetPage:Int=1
    private var mMaxPage:Int=1

    private lateinit var mGoodsAdapter: GoodsAdapter
    override fun injectComponent() {
        DaggerGoodsComponet.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter
    }

    private fun initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        val refreshViewHolder =  BGANormalRefreshViewHolder(this, true)
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder)
        // 设置整个加载更多控件的背景颜色资源 id
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        // 设置下拉刷新控件的背景颜色资源 id
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
    }

    private fun loadData() {
        mMultiStateView.loading()
        if (intent.getIntExtra(KEY_SEARCH_GOODS_TYPE,0)!=0){
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(KEY_GOODS_KEYWORD),mCurrnetPage)
        }else{
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 0), mCurrnetPage)
        }
    }

    override fun onGetGoodsListResult(mutableList: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (mutableList != null && mutableList.size > 0) {
            mMaxPage=mutableList[0].maxPage
            if (mCurrnetPage==1){
                mGoodsAdapter.setData(mutableList)
            }else{
                mGoodsAdapter.dataList.addAll(mutableList)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrnetPage<mMaxPage){
            mCurrnetPage++
            loadData()
            true
        }else{
            false
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrnetPage=1
        loadData()
    }
}