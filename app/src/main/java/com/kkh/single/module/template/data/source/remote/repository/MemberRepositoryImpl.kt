package com.kkh.single.module.template.data.source.remote.repository

import com.kkh.single.module.template.data.api.MemberApi
import com.kkh.single.module.template.data.model.response.GetVeganMenuResponse
import com.kkh.single.module.template.data.util.performApiCall
import com.kkh.single.module.template.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberApi: MemberApi
): MemberRepository {
    override suspend fun getVeganMenus(
        memberId: String,
        date: String
    ): Flow<Result<GetVeganMenuResponse>> =
        performApiCall(
            funcName = "getVeganMenus",
            apiCall = { memberApi.getVeganMenus(memberId, date) }
        )
}