package mx.dev.shellcore.atomicfunctiondemo.core.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class InfoUseCaseImpl @Inject constructor() : InfoUseCase {

    override suspend fun getInfo(): Flow<Result<Info>> {
        return flow {
            delay(2.seconds)
            val info = Info("Atomic Function", "This is a demo of Atomic Function from the use case")
            emit(Result.success(info))
        }
    }
}