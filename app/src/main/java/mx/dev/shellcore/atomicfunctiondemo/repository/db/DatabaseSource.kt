package mx.dev.shellcore.atomicfunctiondemo.repository.db

import mx.dev.shellcore.atomicfunctiondemo.core.model.Info

interface DatabaseSource {
    suspend fun containInfo(): Boolean
    suspend fun getInfo(): Info
    suspend fun saveInfo(info: Info)
}
