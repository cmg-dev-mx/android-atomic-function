package mx.dev.shellcore.atomicfunctiondemo.api.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.atomicfunctiondemo.api.impl.ApiSourceImpl
import mx.dev.shellcore.atomicfunctiondemo.repository.api.ApiSource

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiSourceModule {

    @Binds
    abstract fun bindApiSource(impl: ApiSourceImpl): ApiSource
}