package com.example.brain_station_assessment.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import com.example.brain_station_assessment.R

class LoadingDialog(private val activity: Activity) {

    private lateinit var dialog:AlertDialog

    fun  startLoading() {

        var builder=AlertDialog.Builder(activity)
        var inflater=activity.layoutInflater;
        builder.setView(inflater.inflate(R.layout.custom_loading_dialog,null))
        builder.setCancelable(true)
        dialog=builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.show()

    }
    fun dismissLoading(){
        dialog.dismiss()
    }

}