package mx.dev.shellcore.atomicfunctiondemo.repository.impl

import android.icu.util.Calendar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.atomicfunctiondemo.core.exception.NotUpdatedException
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.core.repository.InfoRepository
import mx.dev.shellcore.atomicfunctiondemo.repository.api.ApiSource
import mx.dev.shellcore.atomicfunctiondemo.repository.db.DatabaseSource
import javax.inject.Inject

class InfoRepositoryImpl @Inject constructor(
    private val dbSource: DatabaseSource,
    private val apiSource: ApiSource
) : InfoRepository {

    override suspend fun getInfo(): Flow<Result<Info>> {
        return flow {
            if (dbSource.containInfo()) {
                emit(Result.success(dbSource.getInfo()))
            }
            if (apiSource.requireUpdateTime()) {
                emit(Result.failure(NotUpdatedException("Require update from API")))
                val info = apiSource.getInfo()
                apiSource.updateTime(Calendar.getInstance().timeInMillis)
                dbSource.saveInfo(info)
                emit(Result.success(info))
            }
        }
    }
}