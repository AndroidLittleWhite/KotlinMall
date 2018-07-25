package com.kotlin.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.common.BaseConstant
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
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
import java.io.File

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener {



    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mTakePhoto.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

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

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null,
                arrayOf("拍照", "相册"), this, AlertView.Style.ActionSheet,
                OnItemClickListener { o, position ->
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
                    when (position) {
                        0 -> {
                            createTempFile()
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }
                        1 -> mTakePhoto.onPickFromGallery()
                    }
                }).show()
    }

    private fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto",result?.image?.compressPath.toString())
        Log.d("TakePhoto",result?.image?.originalPath.toString())

        mLocalFileUri =result?.image?.compressPath.toString()
        mPresenter.getUploadToken()
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto",msg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
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