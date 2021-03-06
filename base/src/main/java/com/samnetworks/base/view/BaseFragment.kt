package com.samnetworks.base.view

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
import com.samnetworks.base.viewmodel.dagger.DaggerViewModelProviderFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<B: ViewDataBinding,V: ViewModel>:Fragment() {
    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelProviderFactory
    private var _binding: B? = null

    protected val binding get() = _binding!!
    protected lateinit var viewModel:V

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        AndroidSupportInjection.inject(this)
        initializeViewModel()
        val binding = initializeViewBinding(inflater,container)
        onFragmentViewCreated(binding)
        return binding.root
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this,daggerViewModelFactory).get(getViewModel())
    }

    private fun initializeViewBinding(inflater: LayoutInflater, container: ViewGroup?) :B{
        _binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        return binding
    }

    final override fun onDestroyView() {
        super.onDestroyView()
        onFragmentViewDestroyed(binding)
        _binding = null
    }

    @LayoutRes
    protected abstract fun getLayoutId():Int
    protected abstract fun getViewModel():Class<V>
    protected abstract fun onFragmentViewCreated(binding:B)
    protected abstract fun onFragmentViewDestroyed(binding:B)
}