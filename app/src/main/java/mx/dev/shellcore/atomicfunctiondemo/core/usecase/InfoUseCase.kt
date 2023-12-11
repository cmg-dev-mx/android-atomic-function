package mx.dev.shellcore.atomicfunctiondemo.core.usecase

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info

interface InfoUseCase {
    suspend fun getInfo(): Flow<Result<Info>>
}
