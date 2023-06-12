package com.marcfearby.common.di

import com.marcfearby.audio.audioPlayerModule
import com.marcfearby.common.settings.mainWindowSettingsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        mainWindowSettingsModule,
        audioPlayerModule
    )
}

fun initKoin() = initKoin {}