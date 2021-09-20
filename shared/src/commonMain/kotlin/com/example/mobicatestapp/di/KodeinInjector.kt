package com.example.mobicatestapp.di

import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.direct
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object KodeinInjector {

    internal val di: DirectDI
        get() = requireNotNull(_di)

    private var _di: DirectDI? = null

    fun init(context: Any) {
        if (_di != null) {
            _di = null
        }

        val direct = DI {
            importAll(
                settingsModule(context = context),
                databaseModule(context = context),
                ktorModule,
                apiModule,
                serializationModule,
                repositoryModule,
                viewModelModule
            )
        }.direct

        _di = direct
    }
}