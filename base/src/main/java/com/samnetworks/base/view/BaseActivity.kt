package com.samnetworks.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samnetworks.base.viewmodel.dagger.DaggerViewModelProviderFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<B:ViewDataBinding,V:ViewModel>:AppCompatActivity() {
    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelProviderFactory
    private var _binding: B? = null

    protected val binding get() = _binding!!
    protected lateinit var viewModel: V

    final override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initializeViewModel()
        initializeViewBinding()
        onActivityCreated(binding)
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this,daggerViewModelFactory).get(getViewModel())
    }

    private fun initializeViewBinding() {
        _binding = DataBindingUtil.setContentView(this,getLayoutId())
    }

    final override fun onDestroy() {
        super.onDestroy()
        onActivityDestroyed(binding)
        _binding = null
    }

    @LayoutRes
    protected abstract fun getLayoutId():Int
    protected abstract fun getViewModel():Class<V>
    protected abstract fun onActivityCreated(binding: B)
    protected abstract fun onActivityDestroyed(binding: B)
}