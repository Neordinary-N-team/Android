package com.kkh.single.module.template.data.api

import com.kkh.single.module.template.data.model.request.MemberIdRequest
import com.kkh.single.module.template.data.model.request.SetMemberInfoRequest
import com.kkh.single.module.template.data.model.response.GetVeganMenuResponse
import com.kkh.single.module.template.data.model.response.MemberInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MemberApi {

    @POST("api/members")
    suspend fun setMemberInfo(
        @Body body: SetMemberInfoRequest
    ): Response<MemberInfoResponse>?

    @GET("api/diets")
    suspend fun getVeganMenus(
        @Query("memberId") memberId: String,
        @Query("date") date: String,
    ): Response<GetVeganMenuResponse>

    @POST("api/diets")
    suspend fun requestCreateVeganMenu(
        @Body body: MemberIdRequest
    ): Response<Unit>
}