package com.samnetworks.base.utils

import kotlin.properties.Delegates

class Observable<T> {
    private val observer = mutableListOf<(T) -> Unit>()
    private var value: T? by Delegates.observable(null) { _, _, newValue ->
        observer.forEach {
            if(newValue!=null) {
                it(newValue)
            }
        }
    }

    fun set(value:T){
        this.value = value
    }

    fun get():T?{
        return value
    }

    fun observe(lambda: (T) -> Unit){
        observer.observe(lambda)
        if(value!=null) {
            lambda(value!!)
        }
    }

    private fun MutableList<(T) -> Unit>.observe(lambda: (T) -> Unit){
        add(lambda)
    }
}