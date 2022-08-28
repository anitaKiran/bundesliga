package com.kickbase.matches.data.model

import com.google.gson.annotations.SerializedName

data class CompetitionsModel(
    val day: Int,
    @SerializedName("e")
    val matchList: List<MatchList>,
    val nd: Int,
    val sn: String
)