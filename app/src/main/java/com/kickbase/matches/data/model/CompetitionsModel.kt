package com.kickbase.matches.data.model

data class CompetitionsModel(
    val day: Int,
    val e: List<MatchList>,
    val nd: Int,
    val sn: String
)