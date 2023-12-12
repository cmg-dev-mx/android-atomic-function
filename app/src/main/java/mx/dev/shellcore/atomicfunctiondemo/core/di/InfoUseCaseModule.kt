package mx.dev.shellcore.atomicfunctiondemo.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.atomicfunctiondemo.core.usecase.InfoUseCase
import mx.dev.shellcore.atomicfunctiondemo.core.usecase.InfoUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class InfoUseCaseModule {

    @Binds
    abstract fun bindInfoUseCase(impl: InfoUseCaseImpl): InfoUseCase
}