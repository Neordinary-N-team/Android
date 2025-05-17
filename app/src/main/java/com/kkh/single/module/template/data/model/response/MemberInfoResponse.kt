package com.kkh.single.module.template.data.model.response

data class MemberInfoResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: String
)
