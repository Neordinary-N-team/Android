package com.kkh.single.module.template.data.api

import com.kkh.single.module.template.data.model.request.SetMemberInfoRequest
import com.kkh.single.module.template.data.model.response.MemberInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberApi {

    @POST("api/members")
    suspend fun setMemberInfo(
        @Body body: SetMemberInfoRequest
    ): Response<MemberInfoResponse>?
}