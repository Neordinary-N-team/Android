package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkh.single.module.template.data.model.request.SetMemberInfoRequest
import com.kkh.single.module.template.data.source.local.LocalDataSource
import com.kkh.single.module.template.data.source.remote.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) :
    ViewModel() {

    private var _disableStop = MutableStateFlow(false)
    val disableStop: StateFlow<Boolean>
        get() = _disableStop

    val pregDate: MutableStateFlow<String> = MutableStateFlow("2024-10-01")
    val height: MutableStateFlow<Int> = MutableStateFlow(160)
    val weight: MutableStateFlow<Int> = MutableStateFlow(60)
    val disease: MutableStateFlow<String> = MutableStateFlow("메스꺼움")
    val prePregnant: MutableStateFlow<Int> = MutableStateFlow(0)
    val hasMorningSickness: MutableStateFlow<String> = MutableStateFlow("NONE")
    val allowedVeganFoods: MutableStateFlow<List<String>> = MutableStateFlow(listOf("과일, 채소"))
    val bannedVegetables: MutableStateFlow<String> = MutableStateFlow("")
    val memberLevel: MutableStateFlow<Int> = MutableStateFlow(1)

    fun requestSetUserInfo(onNavigate: () -> Unit){
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
            val res = remoteDataSource.setMemberInfo(request = request)
            if (res?.body()?.result!= null){
                localDataSource.saveCustomText(res.body()?.result!!.id)
                onNavigate()
            }
        }
    }

    val isUserIdExist = MutableStateFlow(false)

    fun checkUserIdExist() {
        viewModelScope.launch {
            val userId = localDataSource.getCustomText()
            isUserIdExist.value = !userId.isNullOrBlank()
        }
    }


}