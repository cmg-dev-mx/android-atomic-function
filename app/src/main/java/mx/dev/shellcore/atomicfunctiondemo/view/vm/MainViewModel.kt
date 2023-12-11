package mx.dev.shellcore.atomicfunctiondemo.view.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.atomicfunctiondemo.core.usecase.InfoUseCase
import mx.dev.shellcore.atomicfunctiondemo.view.model.InfoVO
import mx.dev.shellcore.atomicfunctiondemo.view.util.LoadingStatus
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: InfoUseCase): ViewModel() {

    private val _infoVO = MutableStateFlow(InfoVO())
    val infoVO = _infoVO.asStateFlow()

    fun loadInfo() {
        viewModelScope.launch {
            _infoVO.value = InfoVO(loading = LoadingStatus.LOADING)
            useCase.getInfo().collect { result ->
                result.onSuccess {
                    _infoVO.value = InfoVO(it, LoadingStatus.SUCCESS)
                }.onFailure {
                    _infoVO.value = InfoVO(loading = LoadingStatus.ERROR)
                }
            }
        }
    }
}