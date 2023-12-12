package mx.dev.shellcore.atomicfunctiondemo.api.impl

import kotlinx.coroutines.delay
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.repository.api.ApiSource
import java.lang.System.currentTimeMillis
import java.util.Calendar
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class ApiSourceImpl @Inject constructor() : ApiSource {



    override suspend fun getInfo(): Info {
        delay(5.seconds)
        return Info("Atomic Function", "This is a demo of Atomic Function from the api")
    }
}