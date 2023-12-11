package mx.dev.shellcore.atomicfunctiondemo.view.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.atomicfunctiondemo.core.model.Info
import mx.dev.shellcore.atomicfunctiondemo.view.model.InfoVO
import mx.dev.shellcore.atomicfunctiondemo.view.util.LoadingStatus
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _infoVO = MutableStateFlow(InfoVO())
    val infoVO = _infoVO.asStateFlow()

    fun loadInfo() {
        viewModelScope.launch {
            _infoVO.value = InfoVO(loading = LoadingStatus.LOADING)
            delay(2.seconds)
            val info = Info("Atomic Function", "This is a demo of Atomic Function")
            _infoVO.value = InfoVO(info = info, loading = LoadingStatus.SUCCESS)
        }
    }
}