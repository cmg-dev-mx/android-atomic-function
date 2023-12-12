package mx.dev.shellcore.atomicfunctiondemo.core.usecase

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.core.repository.InfoRepository
import javax.inject.Inject

class SecondaryInfoUseCaseImpl @Inject constructor(
    private val repository: InfoRepository
) : SecondaryInfoUseCase {

    override suspend fun getInfo() = repository.getInfo()
}