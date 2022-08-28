package com.kickbase.matches.utils

import com.kickbase.matches.data.model.CompetitionsModel

/**
 * Created by Anita Kiran on 8/27/2022.
 */
data class DataState (
    val isLoading: Boolean = false,
    val data: CompetitionsModel? = null,
    val error: String = ""
)