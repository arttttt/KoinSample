package com.arttttt.koinsample.objects

import timber.log.Timber

class ComponentA(
    val componentB: ComponentB
) {
    fun print() {
        Timber.e("this = $this, dep = $componentB")
    }
}