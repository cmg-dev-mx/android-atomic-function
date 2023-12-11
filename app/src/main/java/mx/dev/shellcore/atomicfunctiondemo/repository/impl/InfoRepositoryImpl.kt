package mx.dev.shellcore.atomicfunctiondemo.repository.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.core.repository.InfoRepository
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class InfoRepositoryImpl @Inject constructor() : InfoRepository {

    override suspend fun getInfo(): Flow<Result<Info>> {
        return flow {
            delay(2.seconds)
            val info = Info("Atomic Function", "This is a demo of Atomic Function from the repository")
            emit(Result.success(info))
        }
    }
}