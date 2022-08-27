package com.kickbase.matches.data.repository

import com.kickbase.matches.data.model.CompetitionsModel
import com.kickbase.matches.data.network.KickbaseServiceApi
import javax.inject.Inject

/**
 * Created by Anita Kiran on 8/27/2022.
 */
class MatchesRepository @Inject constructor(private val apiService: KickbaseServiceApi) {
    suspend fun fetchMatches() : CompetitionsModel = apiService.fetchMatches()
}