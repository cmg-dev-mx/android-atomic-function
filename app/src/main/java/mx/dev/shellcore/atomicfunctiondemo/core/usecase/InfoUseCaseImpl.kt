package mx.dev.shellcore.atomicfunctiondemo.core.usecase

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.core.repository.InfoRepository
import javax.inject.Inject

class InfoUseCaseImpl @Inject constructor(private val repository: InfoRepository) : InfoUseCase {

    override suspend fun getInfo(): Flow<Result<Info>> {
        return repository.getInfo()
    }
}