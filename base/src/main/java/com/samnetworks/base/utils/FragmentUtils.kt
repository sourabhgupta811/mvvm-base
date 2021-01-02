package com.samnetworks.base.utils

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {
    fun showDialogFragment(supportFragmentManager: FragmentManager, dialogFragment: DialogFragment){
        val ft = supportFragmentManager.beginTransaction()
        val tag = dialogFragment::class.java.simpleName
        val prev = supportFragmentManager.findFragmentByTag(tag)
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        dialogFragment.show(ft, tag)
    }
}