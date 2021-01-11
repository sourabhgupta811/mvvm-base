package com.samnetworks.base.dagger

import com.samnetworks.base.application.MvvmApplication

/**
 * Add these modules when using this componenet
 * AndroidSupportInjectionModule::class,AndroidInjectionModule::class,DaggerViewModelModule::class
 */
interface DaggerComponent {
    fun <T:MvvmApplication> inject(mvvmApplication :T)
}