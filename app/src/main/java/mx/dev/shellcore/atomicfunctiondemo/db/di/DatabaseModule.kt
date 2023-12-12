package mx.dev.shellcore.atomicfunctiondemo.db.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.atomicfunctiondemo.db.impl.DatabaseSourceImpl
import mx.dev.shellcore.atomicfunctiondemo.repository.db.DatabaseSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    abstract fun bindDatabaseSource(impl: DatabaseSourceImpl): DatabaseSource
}