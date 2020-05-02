package com.arttttt.koinsample.di

import com.arttttt.koinsample.ui.a.moduleA
import com.arttttt.koinsample.ui.b.moduleB
import org.koin.dsl.module

private val appModule= module {
}

val appModules = listOf(
    appModule,
    moduleA,
    moduleB
)
