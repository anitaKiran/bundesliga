package com.kickbase.matches.data.repository

import com.kickbase.matches.data.model.CompetitionsModel
import com.kickbase.matches.data.network.KickbaseServiceApi
import com.kickbase.matches.utils.AppConstants
import com.kickbase.matches.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Anita Kiran on 8/27/2022.
 */
class MatchRepository @Inject constructor(private val apiService: KickbaseServiceApi) {

    operator fun invoke(): Flow<Resource<CompetitionsModel>> = flow() {
        try {
            emit(Resource.Loading())
            // fetch list of football matches from api
            val response = apiService.fetchMatches()
            if (response != null)
                emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: AppConstants.UNKNOWN_ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Resource.Error(AppConstants.CHECK_CONNECTIVITY))
        } catch (e: Exception) {
        }
    }
}