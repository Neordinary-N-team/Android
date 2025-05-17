package com.kkh.single.module.template.data.source.remote

import com.kkh.single.module.template.data.api.MemberApi
import com.kkh.single.module.template.data.model.request.SetMemberInfoRequest
import com.kkh.single.module.template.data.model.response.MemberInfoResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val memberApi: MemberApi) {

    suspend fun setMemberInfo(request: SetMemberInfoRequest): Response<MemberInfoResponse>? {
        return try {
            return memberApi.setMemberInfo(request)
        } catch (e: Exception) {
            null
        }

    }
}