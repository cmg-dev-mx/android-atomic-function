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

    private var updatedTime: Long = 0L

    override suspend fun getInfo(): Flow<Result<Info>> {
        return flow {
            if (dbSource.containInfo()) {
                emit(Result.success(dbSource.getInfo()))
            }
            if (requireUpdateTime()) {
                emit(Result.failure(NotUpdatedException("Require update from API")))
                val info = apiSource.getInfo()
                dbSource.saveInfo(info)
                updateTime(Calendar.getInstance().timeInMillis)
                emit(Result.success(info))
            }
        }
    }

    private fun requireUpdateTime(): Boolean {
        return if (updatedTime == 0L) {
            true
        } else {
            val currentTime = java.util.Calendar.getInstance().timeInMillis
            currentTime - updatedTime > 10000 // 10 seconds
        }
    }

    private fun updateTime(timeInMillis: Long) {
        updatedTime = timeInMillis
    }
}