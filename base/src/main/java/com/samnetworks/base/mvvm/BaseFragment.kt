package com.samnetworks.base.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samnetworks.base.viewmodel.DaggerViewModelProviderFactory
import javax.inject.Inject

abstract class BaseFragment<B: ViewDataBinding,V: ViewModel>:Fragment() {
    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelProviderFactory

    protected lateinit var binding:B
    protected lateinit var viewModel:V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectFragment()
        initializeViewModel()
        val binding = initializeViewBinding(inflater,container)
        onFragmentInitialised(binding)
        return binding.root
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this,daggerViewModelFactory).get(getViewModel())
    }

    private fun initializeViewBinding(inflater: LayoutInflater, container: ViewGroup?) :B{
        binding = DataBindingUtil.inflate<B>(inflater,getLayoutId(),container,false)
        return binding
    }

    abstract fun onFragmentInitialised(binding:B)

    @LayoutRes
    protected abstract fun getLayoutId():Int

    protected abstract fun getViewModel():Class<V>
    protected abstract fun injectFragment()
}