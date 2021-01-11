package com.samnetworks.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel :ViewModel(){
    /**
     * coroutine scope for background IO Thread
     */
    protected val mIoScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)
    override fun onCleared() {
        super.onCleared()
        mIoScope.cancel()
    }
}