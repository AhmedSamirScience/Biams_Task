package com.samir.baims.common.utils.widget

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.samir.baims.R


object CustomAlertDialog {

    /*
    fun showDialogLogout(context: Context, firstAction: (() -> Unit)?) {
        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialog).create()

        val view = LayoutInflater.from(context).inflate(R.layout.alertdialog_logout, null)

        val proceedBtn = view.findViewById<AppCompatButton>(R.id.btn_confirm)
        val dismissBtn = view.findViewById<AppCompatButton>(R.id.btn_back)
        builder.setView(view)

        proceedBtn.setOnClickListener {
            builder.dismiss()
            firstAction?.invoke()
        }
        dismissBtn.setOnClickListener {
            builder.dismiss()
        }

        builder.setCanceledOnTouchOutside(false)
        builder.setCancelable(false)

        builder.show()
    }
    fun initAndShowAlertDialog(messageAlert: String, ctx : Context) : androidx.appcompat.app.AlertDialog {

        val builderAlert = androidx.appcompat.app.AlertDialog.Builder(ctx, R.style.CustomAlertDialog).create()

        val view = LayoutInflater.from(ctx).inflate(R.layout.dialog_loading, null)
        val textMessage = view.findViewById<TextView>(R.id.textView)
        textMessage.text = messageAlert

        builderAlert.setView(view)
        builderAlert.setCanceledOnTouchOutside(false)
        builderAlert.setCancelable(false)
        if (!builderAlert.isShowing) {
            builderAlert.show()
        }

        return builderAlert
    }

     */
    fun showDialogErrorWithActionButton(act: Context, message: String, firstAction: (() -> Unit)) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(act, R.style.CustomAlertDialog).create()
        val view = LayoutInflater.from(act).inflate(R.layout.dialoge_error, null)

        val dismissBtn = view.findViewById<AppCompatButton>(R.id.btn_dismiss)
        val textMessage = view.findViewById<TextView>(R.id.txt_message)
        textMessage.text = message
        builder.setView(view)
        //builder.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent) to make the alert transparent

        dismissBtn.setOnClickListener {
            builder.dismiss()
            firstAction.invoke()
        }

        builder.setCanceledOnTouchOutside(false)
        builder.setCancelable(false)

        builder.show()
    }
    fun initAndShowAlertDialog(messageAlert: String, ctx : Context) : androidx.appcompat.app.AlertDialog {

        val builderAlert = androidx.appcompat.app.AlertDialog.Builder(ctx, R.style.CustomAlertDialog).create()

        val view = LayoutInflater.from(ctx).inflate(R.layout.dialog_loading, null)
        val textMessage = view.findViewById<TextView>(R.id.textView)
        textMessage.text = messageAlert

        builderAlert.setView(view)
        builderAlert.setCanceledOnTouchOutside(false)
        builderAlert.setCancelable(false)
        if (!builderAlert.isShowing) {
            builderAlert.show()
        }

        return builderAlert
    }



}