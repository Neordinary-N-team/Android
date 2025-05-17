package com.kkh.single.module.template.data.source.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.UUID

class LocalDataSource @Inject constructor(private val dataStoreManager: DataStoreManager) {

    private val keyString = stringPreferencesKey("CUSTOM_TEXT")
    private val keyBoolean = booleanPreferencesKey("CUSTOM_BOOL")
    private val keyInt = intPreferencesKey("CUSTOM_INT")
    private val keyUserId = stringPreferencesKey("USER_UUID")

    suspend fun saveCustomText(saveString: String) {
        dataStoreManager.saveString(keyString, saveString)
    }

    // 한 번만 값을 얻고 싶다면.
    suspend fun getCustomText(): String {
        return dataStoreManager.readString(keyString).first()
    }

    // 변화를 계속 관찰하고 싶다면.
    fun observeCustomText(): Flow<String> {
        return dataStoreManager.readString(keyString)
    }

    suspend fun saveUserId(uuid: String) {
        dataStoreManager.saveString(keyUserId, uuid)
    }

    suspend fun getUserId(): String {
        val userId = dataStoreManager.readString(keyUserId).first()
        return if (userId.isEmpty()) {
            // UUID가 없으면 새로 생성하고 저장
            val newUserId = UUID.randomUUID().toString()
            saveUserId(newUserId)
            newUserId
        } else {
            userId
        }
    }

}