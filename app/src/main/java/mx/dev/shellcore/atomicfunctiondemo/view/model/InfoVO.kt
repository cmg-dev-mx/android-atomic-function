package mx.dev.shellcore.atomicfunctiondemo.view.model

import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.view.util.LoadingStatus

data class InfoVO(
    val info: Info? = null,
    val loading: LoadingStatus = LoadingStatus.SUCCESS
)
