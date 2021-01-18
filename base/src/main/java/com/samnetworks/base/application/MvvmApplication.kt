package com.samnetworks.base.application

import android.app.Application
import com.samnetworks.base.dagger.IDaggerComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class MvvmApplication:Application(),HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        provideDaggerComponent().inject(this)
    }

    abstract fun provideDaggerComponent():IDaggerComponent<MvvmApplication>

    override fun androidInjector(): DispatchingAndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}