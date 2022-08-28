package com.kickbase.matches.data.model

import com.google.gson.annotations.SerializedName

data class TeamOne(
    val g: Int,
    @SerializedName("i")
    val clubId: String,
    @SerializedName("n")
    val clubName: String,
    val p: Int,
    @SerializedName("s")
    val clubShortName: String,
    val sp: Int
)