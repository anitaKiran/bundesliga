package com.kickbase.matches.data.model

import com.google.gson.annotations.SerializedName

data class MatchList(
    val bo: Bo,
    @SerializedName("d")
    val dateOfMatch: String,
    val i: String,
    val mt: Int,
    val s: Int,
    val t1: TeamInfo,
    val t2: TeamInfo
)