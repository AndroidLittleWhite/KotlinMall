package com.kotlin.usercenter.ui.activity

import android.os.Bundle
import android.util.Log
import com.jph.takephoto.model.TResult
import com.kotlin.base.common.BaseConstant
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseTakePhotoActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.utils.UserPrefsUtils.putUserInfo
import com.kotlin.usercenter.data.protocol.UserInfo
import com.kotlin.usercenter.injection.component.DaggerUserComponet
import com.kotlin.usercenter.injection.module.UserModule
import com.kotlin.usercenter.presenter.UserInfoPresenter
import com.kotlin.usercenter.presenter.view.UserInfoView
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import com.yking.usercenter.R
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject

class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView {

    private val mUploadManager:UploadManager by lazy { UploadManager() }

    private var mLocalFileUri:String?=null

    private var mRemoteFileUrl:String?=""

    private var mUserIcon:String?=null
    private var mUserName:String?=null
    private var mUserMobile:String?=null
    private var mUserGender:String?=null
    private var mUserSign:String?=null

    override fun injectComponent() {
        DaggerUserComponet.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)



        initView()
        initData()

    }

    private fun initView() {

        mUserIconView.onClick {
            showAlertView()
        }
        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!,
                    mUserNameEt?.text?.toString()?:"",
                    if(mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt?.text?.toString()?:"")
        }
    }

    private fun initData() {
        mUserIcon=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)
        mRemoteFileUrl =mUserIcon

        setupDaa()
    }

    private fun setupDaa() {
        loadIcon()
        mUserNameEt.setText(mUserName)
        if (mUserGender == "0") {
            mGenderMaleRb.isChecked=true
        }else{
            mGenderFemaleRb.isChecked=true
        }
        mUserMobileTv.text=mUserMobile
        mUserSignEt.setText(mUserSign)
    }

    private fun loadIcon() {
        GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!,mUserIconIv)
    }

    override fun takeSuccess(result: TResult?) {

        mLocalFileUri =result?.image?.compressPath.toString()
        mPresenter.getUploadToken()
    }

    override fun onGetUploadTokenResult(token: String) {
        mUploadManager.put(mLocalFileUri,null,token,object: UpCompletionHandler{
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFileUrl =BaseConstant.IMAGE_SERVER_ADDRESS+response?.get("hash")
                Log.d("RemotePath", mRemoteFileUrl)
                loadIcon()
            }
        },null)
    }
    override fun onEditUserResult(user: UserInfo) {
        toast("保存成功")
        putUserInfo(user)
    }
}