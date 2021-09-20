package com.example.mobicatestapp.di

import com.example.mobicatestapp.settings.UserSettingsFactory
import com.russhwolf.settings.Settings
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

internal fun settingsModule(context: Any) = DI.Module(
    name = "SettingsModule",
    init = {
        bindSingleton {
            UserSettingsFactory().createUserSettings(context = context)!!
        }
    }
)

@InternalCoroutinesApi
@ThreadLocal
object SettingsModule {
    val settings: Settings
        get() = KodeinInjector.di.instance()
}

@InternalCoroutinesApi
val KodeinInjector.settingsModuleExt: SettingsModule
    get() = SettingsModule