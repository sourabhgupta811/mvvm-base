package com.samnetworks.base.viewmodel.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import javax.inject.Named
import kotlin.reflect.KClass

/**
    @Binds
    @IntoMap
    @DaggerViewModelModule.ViewModelKey(SplashActivityViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashActivityViewModel):ViewModel
 */
@Module
abstract class DaggerViewModelModule {
    companion object{
        const val BASE_VIEW_MODEL_FACTORY = "BASE_VIEW_MODEL_FACTORY"
    }
    @MustBeDocumented
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value:KClass<out ViewModel>)

    @Named(BASE_VIEW_MODEL_FACTORY)
    @Binds
    abstract fun bindViewModelFactory(factoryDagger: DaggerViewModelProviderFactory):ViewModelProvider.Factory
}