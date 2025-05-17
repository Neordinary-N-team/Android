package com.kkh.single.module.template.presentation.screen.recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkh.single.module.template.data.model.response.GetVeganMenuResponse
import com.kkh.single.module.template.data.model.response.VeganMenu
import com.kkh.single.module.template.data.source.local.LocalDataSource
import com.kkh.single.module.template.domain.usecase.GetVeganMenusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getVeganMenusUseCase: GetVeganMenusUseCase,
    private val localDataSource: LocalDataSource,
): ViewModel() {

    private val TAG = this::class.simpleName

    private val userId = viewModelScope.async {
        localDataSource.getCustomText()
    }
    val allowedVeganFoods: MutableStateFlow<List<VeganMenu>> = MutableStateFlow(emptyList())

    fun getVeganMenus(date: String) = viewModelScope.launch(Dispatchers.IO) {
        allowedVeganFoods.value = emptyList()
        val id = userId.await()
        try {
            getVeganMenusUseCase.invoke(id, date).collect { result ->
                result.onSuccess { response: GetVeganMenuResponse ->
                    Log.e(TAG, "## [getVeganMenus] success : $response")
                    result.getOrNull()?.result?.forEach { veganMenu ->
                        allowedVeganFoods.update { currentList ->
                            currentList + veganMenu // 새로운 VeganMenu 항목을 기존 리스트에 추가
                        }
                    }
                }.onFailure { e ->
                    Log.e(TAG, "## [getVeganMenus] failed : ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "## [getVeganMenus] error : ${e.message}")
        }
    }

}