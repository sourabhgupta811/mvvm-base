package com.samnetworks.base.mvvm

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samnetworks.base.viewmodel.DaggerViewModelProviderFactory
import javax.inject.Inject

abstract class BaseActivity<B:ViewDataBinding,V:ViewModel>:AppCompatActivity() {
    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelProviderFactory

    protected lateinit var binding:B
    protected lateinit var viewModel: V
    override fun onCreate(savedInstanceState: Bundle?) {
        injectActivity()
        super.onCreate(savedInstanceState)
        initializeViewModel()
        initializeViewBinding()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this,daggerViewModelFactory).get(getViewModel())
    }

    private fun initializeViewBinding() {
        binding = DataBindingUtil.setContentView(this,getLayoutId())
    }

    @LayoutRes
    protected abstract fun getLayoutId():Int
    protected abstract fun getViewModel():Class<V>
    protected abstract fun injectActivity()
}