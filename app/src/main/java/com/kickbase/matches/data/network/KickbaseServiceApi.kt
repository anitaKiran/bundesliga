package com.kickbase.matches.data.network

import com.kickbase.matches.data.model.CompetitionsModel
import retrofit2.http.GET

/**
 * Created by Anita Kiran on 8/27/2022.
 */
interface KickbaseServiceApi {

    // get list of bundelisga matches from api
    @GET("competitions/2/matches")
    suspend fun fetchMatches() : CompetitionsModel
}