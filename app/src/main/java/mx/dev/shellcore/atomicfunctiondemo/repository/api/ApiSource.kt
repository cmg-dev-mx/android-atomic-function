package mx.dev.shellcore.atomicfunctiondemo.repository.api

import mx.dev.shellcore.atomicfunctiondemo.core.model.Info

interface ApiSource {
    suspend fun getInfo(): Info
}
