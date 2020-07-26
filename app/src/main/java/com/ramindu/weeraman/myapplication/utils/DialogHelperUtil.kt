package com.ramindu.weeraman.myapplication.utils


import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Toast
import com.ramindu.weeraman.myapplication.R
import javax.inject.Inject


class DialogHelperUtil @Inject constructor() {
    private var progressDialog: Dialog? = null

    fun showToast(context: Context?, message: CharSequence) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgressBar(context: Context?) {
        hideProgressBar()
        context?.let {
            progressDialog = Dialog(it)
            progressDialog?.apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.progress_bar)
                window?.setBackgroundDrawableResource(R.color.transparent)
                setCancelable(false)
                show()
            }
        }

    }

    fun hideProgressBar() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }


}