package mx.dev.shellcore.atomicfunctiondemo.repository.impl

import android.icu.util.Calendar
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import mx.dev.shellcore.atomicfunctiondemo.core.exception.NotUpdatedException
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.core.repository.InfoRepository
import mx.dev.shellcore.atomicfunctiondemo.repository.api.ApiSource
import mx.dev.shellcore.atomicfunctiondemo.repository.db.DatabaseSource
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.withLock

@Singleton
class InfoRepositoryImpl @Inject constructor(
    private val dbSource: DatabaseSource,
    private val apiSource: ApiSource
) : InfoRepository {

    private val mutex = Mutex()
    private var updatedTime: Long = 0L

    override suspend fun getInfo(): Flow<Result<Info>> {
        return flow {
            mutex.withLock {
                Log.d("InfoRepositoryImpl", "LOADING FROM REPOSITORY (${this@InfoRepositoryImpl})")
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