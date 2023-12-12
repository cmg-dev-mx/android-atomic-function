package mx.dev.shellcore.atomicfunctiondemo.core.repository

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info

interface InfoRepository {
    suspend fun getInfo(): Flow<Result<Info>>
}
