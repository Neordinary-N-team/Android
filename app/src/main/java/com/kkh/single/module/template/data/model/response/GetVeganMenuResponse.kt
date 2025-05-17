package com.kkh.single.module.template.data.model.response

import com.google.gson.annotations.SerializedName

data class GetVeganMenuResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean?,
    @SerializedName("code") val code: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: List<VeganMenu>?,
)

data class VeganMenu(
    @SerializedName("id") val id: Long?,
    @SerializedName("date") val date: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("mealType") val mealType: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("calories") val calories: Int?,
    @SerializedName("difficulty") val difficulty: String?,
)