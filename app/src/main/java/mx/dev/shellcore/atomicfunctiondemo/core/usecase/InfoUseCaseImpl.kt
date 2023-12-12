package mx.dev.shellcore.atomicfunctiondemo.core.usecase

import mx.dev.shellcore.atomicfunctiondemo.core.repository.InfoRepository
import javax.inject.Inject
import javax.inject.Singleton

class InfoUseCaseImpl @Inject constructor(
    private val repository: InfoRepository
) : InfoUseCase {

    override suspend fun getInfo() = repository.getInfo()
}