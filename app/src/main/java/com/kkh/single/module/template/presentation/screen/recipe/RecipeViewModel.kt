package com.kkh.single.module.template.presentation.screen.recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkh.single.module.template.data.model.response.GetVeganMenuResponse
import com.kkh.single.module.template.data.source.local.LocalDataSource
import com.kkh.single.module.template.domain.usecase.GetVeganMenusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    fun getVeganMenus(date: String) = viewModelScope.launch(Dispatchers.IO) {
        val id = userId.await()
        try {
            getVeganMenusUseCase.invoke(id, date).collect { result ->
                result.onSuccess { response: GetVeganMenuResponse ->
                    Log.e(TAG, "## [getVeganMenus] success : $response")
                }.onFailure { e ->
                    Log.e(TAG, "## [getVeganMenus] failed : ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "## [getVeganMenus] error : ${e.message}")
        }
    }

}