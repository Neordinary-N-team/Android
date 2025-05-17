package com.kkh.single.module.template.data.model.response

data class MemberInfoResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: MemberInfo
){
    data class MemberInfo(
        val id: String,
        val pregDate: String,
        val height: Int,
        val weight: Int,
        val diseases: String,
        val prePregnant: Int,
        val hasMorningSickness: String,
        val allowedVeganFoods: List<String>,
        val bannedVegetables: String,
        val memberLevel: Int
    )
}


