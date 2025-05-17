package com.kkh.single.module.template.domain.usecase

import com.kkh.single.module.template.data.model.response.GetVeganMenuResponse
import com.kkh.single.module.template.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVeganMenusUseCase @Inject constructor(
    private val repository: MemberRepository
) {
    suspend operator fun invoke(
        memberId: String,
        date: String
    ): Flow<Result<GetVeganMenuResponse>> =
        repository.getVeganMenus(memberId, date)
}