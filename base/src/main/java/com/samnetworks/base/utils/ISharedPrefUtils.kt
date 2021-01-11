package com.samnetworks.base.utils

import android.app.Application
import android.content.Context.MODE_PRIVATE

class ISharedPrefUtils(application: Application,sharedPrefName:String){
    private val sharedPref by lazy{
        application.getSharedPreferences(sharedPrefName, MODE_PRIVATE)
    }

    fun putString(key:String,value:String){
        sharedPref.edit().putString(key,value).apply()
    }
    fun putBoolean(key:String,value:Boolean){
        sharedPref.edit().putBoolean(key,value).apply()
    }
    fun putFloat(key:String,value:Float){
        sharedPref.edit().putFloat(key,value).apply()
    }
    fun putLong(key:String,value:Long){
        sharedPref.edit().putLong(key,value).apply()
    }
    fun putInt(key:String,value:Int){
        sharedPref.edit().putInt(key,value).apply()
    }
    fun getString(key:String,defaultValue:String? = null):String?{
        return sharedPref.getString(key,defaultValue)
    }
    fun getBoolean(key:String,defaultValue:Boolean = false):Boolean{
        return sharedPref.getBoolean(key,defaultValue)
    }
    fun getFloat(key:String,defaultValue:Float = 0f):Float{
        return sharedPref.getFloat(key,defaultValue)
    }
    fun getLong(key:String, defaultValue:Long = 0L):Long{
        return sharedPref.getLong(key,defaultValue)
    }
    fun getInt(key:String, defaultValue:Int = 0):Int{
        return sharedPref.getInt(key,defaultValue)
    }
}