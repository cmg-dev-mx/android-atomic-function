package mx.dev.shellcore.atomicfunctiondemo.api.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.atomicfunctiondemo.api.impl.ApiSourceImpl
import mx.dev.shellcore.atomicfunctiondemo.repository.api.ApiSource

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiSourceModule {

    @Binds
    abstract fun bindApiSource(impl: ApiSourceImpl): ApiSource
}