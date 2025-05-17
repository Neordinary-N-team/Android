package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkh.single.module.template.data.model.request.SetMemberInfoRequest
import com.kkh.single.module.template.data.source.remote.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) : ViewModel(){

    private var _disableStop = MutableStateFlow(false)
    val disableStop: StateFlow<Boolean>
        get() = _disableStop

    val pregDate: MutableStateFlow<String> = MutableStateFlow("")
    val height: MutableStateFlow<Int> = MutableStateFlow(0)
    val weight: MutableStateFlow<Int> = MutableStateFlow(0)
    val disease: MutableStateFlow<String> = MutableStateFlow("")
    val prePregnant: MutableStateFlow<Int> = MutableStateFlow(0)
    val hasMorningSickness: MutableStateFlow<String> = MutableStateFlow("")
    val allowedVeganFoods: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val bannedVegetables : MutableStateFlow<String> = MutableStateFlow("")
    val memberLevel : MutableStateFlow<Int> = MutableStateFlow(0)

    fun requestSetUserInfo(){
        val request = SetMemberInfoRequest(
            pregDate = pregDate.value,
            height = height.value,
            weight = weight.value,
            diseases = disease.value,
            prePregnant = prePregnant.value,
            hasMorningSickness = hasMorningSickness.value,
            allowedVeganFoods = allowedVeganFoods.value,
            bannedVegetables = bannedVegetables.value,
            memberLevel = memberLevel.value
        )
        viewModelScope.launch {
            remoteDataSource.setMemberInfo(request = request)
        }
    }
}