package mx.dev.shellcore.atomicfunctiondemo.api.impl

import kotlinx.coroutines.delay
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.repository.api.ApiSource
import java.lang.System.currentTimeMillis
import java.util.Calendar
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class ApiSourceImpl @Inject constructor() : ApiSource {

    private var updatedTime: Long = 0L

    override suspend fun requireUpdateTime(): Boolean {
        return if (updatedTime == 0L) {
            true
        } else {
            val currentTime = Calendar.getInstance().timeInMillis
            currentTime - updatedTime > 10000 // 10 seconds
        }
    }

    override suspend fun updateTime(timeInMillis: Long) {
        updatedTime = timeInMillis
    }

    override suspend fun getInfo(): Info {
        delay(2.seconds)
        return Info("Atomic Function", "This is a demo of Atomic Function from the api")
    }
}