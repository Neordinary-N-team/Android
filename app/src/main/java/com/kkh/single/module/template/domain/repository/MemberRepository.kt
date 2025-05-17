package com.kkh.single.module.template.domain.repository

import com.kkh.single.module.template.data.model.response.GetVeganMenuResponse
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    suspend fun getVeganMenus(
        memberId: String,
        date: String,
    ): Flow<Result<GetVeganMenuResponse>>
}