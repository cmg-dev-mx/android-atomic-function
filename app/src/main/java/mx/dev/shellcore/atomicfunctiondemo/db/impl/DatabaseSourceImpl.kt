package mx.dev.shellcore.atomicfunctiondemo.db.impl

import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.repository.db.DatabaseSource
import javax.inject.Inject

class DatabaseSourceImpl @Inject constructor() : DatabaseSource {

    private var info: Info? = null

    override suspend fun containInfo(): Boolean {
        return info != null
    }

    override suspend fun getInfo(): Info {
        return Info("Atomic Function", "This is a demo of Atomic Function from the data base")
    }

    override suspend fun saveInfo(info: Info) {
        this.info = info
    }
}