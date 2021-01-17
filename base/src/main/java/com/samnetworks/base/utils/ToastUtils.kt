package com.samnetworks.base.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun showShortToast(context: Context,message:String){
    Handler(Looper.getMainLooper()).post{
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}

fun showLongToast(context: Context,message:String){
    Handler(Looper.getMainLooper()).post{
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
}