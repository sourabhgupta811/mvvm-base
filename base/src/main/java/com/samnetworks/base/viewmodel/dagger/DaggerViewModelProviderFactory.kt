package com.samnetworks.base.viewmodel.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Provider

class DaggerViewModelProviderFactory @Inject constructor(
    private val creators:Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?:creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("unknown model class ${modelClass.simpleName}")
        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        }catch (exception:Exception){
            throw RuntimeException(exception)
        }
    }
}