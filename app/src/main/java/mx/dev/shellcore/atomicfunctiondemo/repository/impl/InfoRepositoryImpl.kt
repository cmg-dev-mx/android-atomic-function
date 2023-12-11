package mx.dev.shellcore.atomicfunctiondemo.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.core.repository.InfoRepository
import mx.dev.shellcore.atomicfunctiondemo.repository.db.DatabaseSource
import javax.inject.Inject

class InfoRepositoryImpl @Inject constructor(
    private val dbSource: DatabaseSource
) : InfoRepository {

    override suspend fun getInfo(): Flow<Result<Info>> {
        return flow {
            if (dbSource.containInfo()) {
                emit(Result.success(dbSource.getInfo()))
            } else {
                emit(Result.failure(Exception("Info not found")))
            }
        }
    }
}