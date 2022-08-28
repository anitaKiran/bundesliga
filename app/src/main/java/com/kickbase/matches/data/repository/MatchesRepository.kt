package com.kickbase.matches.data.repository

import android.util.Log
import com.kickbase.matches.data.model.CompetitionsModel
import com.kickbase.matches.data.network.KickbaseServiceApi
import com.kickbase.matches.utils.AppConstants
import com.kickbase.matches.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Anita Kiran on 8/27/2022.
 */
class MatchesRepository @Inject constructor(private val apiService: KickbaseServiceApi) {
    //suspend fun fetchMatches() : CompetitionsModel = apiService.fetchMatches()

    operator fun invoke(): Flow<Resource<CompetitionsModel>> = flow {
        try {
            emit(Resource.Loading())
            // fetch short link from api
            val response = apiService.fetchMatches()
            if (response != null) {
                Log.e("repository", "$response")
                // insert data in database
                emit(Resource.Success(response))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: AppConstants.UNKNOWN_ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Resource.Error(AppConstants.CHECK_CONNECTIVITY))
        } catch (e: Exception) {
        }
    }
}