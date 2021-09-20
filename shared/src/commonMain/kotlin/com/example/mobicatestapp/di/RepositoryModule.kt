package com.example.mobicatestapp.di

import com.example.mobicatestapp.repository.Repository
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

internal val repositoryModule = DI.Module(name = "RepositoryModule") {
    bindSingleton {
        Repository(
            mobicaApi = instance(),
            userSettings = instance(),
            database = instance()
        )
    }
}

@ThreadLocal
object RepositoryModule {
    val repository: Repository
        get() = KodeinInjector.di.instance()
}

val KodeinInjector.repositoryModuleExt: RepositoryModule
    get() = RepositoryModule