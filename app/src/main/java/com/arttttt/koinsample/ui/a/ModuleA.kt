package com.arttttt.koinsample.ui.a

import com.arttttt.koinsample.objects.ComponentB
import org.koin.dsl.module

val moduleA = module {
    scope<FragmentA> {
        scoped { ComponentB() }
    }
}