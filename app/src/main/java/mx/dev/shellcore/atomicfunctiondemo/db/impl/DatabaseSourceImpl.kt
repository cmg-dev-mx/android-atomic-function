package mx.dev.shellcore.atomicfunctiondemo.db.impl

import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.repository.db.DatabaseSource
import javax.inject.Inject

class DatabaseSourceImpl @Inject constructor() : DatabaseSource {

    private var containInfo = false

    override suspend fun containInfo(): Boolean {
        return containInfo
    }

    override suspend fun getInfo(): Info {
        return Info("Atomic Function", "This is a demo of Atomic Function from the db")
    }
}