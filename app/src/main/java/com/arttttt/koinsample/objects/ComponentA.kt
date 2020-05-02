package com.arttttt.koinsample.objects

import timber.log.Timber

class ComponentA(
    private val componentB: ComponentB
) {
    fun print() {
        Timber.e("this = $this, dep = $componentB")
    }
}