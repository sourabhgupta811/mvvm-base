package com.samnetworks.base.utils

import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {
    fun showDialogFragment(supportFragmentManager: FragmentManager, dialogFragment: DialogFragment){
        val ft = supportFragmentManager.beginTransaction()
        val tag = dialogFragment::class.java.simpleName
        val prev = supportFragmentManager.findFragmentByTag(tag)
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(tag)
        dialogFragment.show(ft, tag)
    }

    fun addFragmentToContainer(fragment: Fragment, @IdRes containerId:Int, fragmentManager: FragmentManager, addToBackStack:Boolean){
        var fragmentTransaction = fragmentManager.beginTransaction().add(containerId,fragment,fragment::class.java.simpleName)
        if(addToBackStack){
            fragmentTransaction = fragmentTransaction.addToBackStack(fragment::class.java.simpleName)
        }
        fragmentTransaction.commit()
    }

    fun removeFragment(fragmentManager: FragmentManager,fragment: Fragment){
        fragmentManager.beginTransaction().remove(fragment).commit()
    }
}