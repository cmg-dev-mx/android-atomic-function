package mx.dev.shellcore.atomicfunctiondemo.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.atomicfunctiondemo.core.repository.InfoRepository
import mx.dev.shellcore.atomicfunctiondemo.repository.impl.InfoRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class InfoRepositoryModule {

    @Binds
    abstract fun bindInfoRepository(impl: InfoRepositoryImpl): InfoRepository
}