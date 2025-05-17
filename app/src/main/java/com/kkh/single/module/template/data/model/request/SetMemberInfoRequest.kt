package com.kkh.single.module.template.data.model.request

data class SetMemberInfoRequest(
    val pregDate: String,              // 임신 날짜 (예: "2025-05-17")
    val height: Int,                   // 키 (cm)
    val weight: Int,                   // 몸무게 (kg)
    val diseases: String,              // 기저 질환 (예: "고혈압")
    val prePregnant: Int,              // 임신 준비 여부 (1 또는 0)
    val hasMorningSickness: String,    // 입덧 상태 (예: "NONE")
    val allowedVeganFoods: List<String>, // 허용된 비건 식품 (예: ["과일", "채소"])
    val bannedVegetables: String,      // 금지된 채소 (예: "오이")
    val memberLevel: Int               // 멤버 등급
)
