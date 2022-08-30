package com.kickbase.matches.data.repository

import com.kickbase.matches.data.model.CompetitionsModel
import com.kickbase.matches.data.model.MatchList
import com.kickbase.matches.data.model.TeamInfo
import com.kickbase.matches.data.network.KickbaseServiceApi
import com.kickbase.matches.ui.viewmodel.BaseTest
import com.kickbase.matches.utils.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Anita Kiran on 8/30/2022.
 */
class MatchRepositoryTest() : BaseTest(){

    private lateinit var repo: MatchRepository
    @Mock
    lateinit var apiService: KickbaseServiceApi


    val teamInfo = TeamInfo(
        goals = 0,
        clubId = "21",
        clubName = "Braunschw.",
        p = 0,
        clubShortName = "FWS",
        sp = 2740
    )

    private val competitionsModel = CompetitionsModel(
        1,
        listOf(
            MatchList(
                "2022-09-02T16:30:00Z",
                i = "6456",
                mt = 1,
                statusOfMatch = 0,
                t1 = teamInfo,
                t2 = teamInfo)), nd = 34, sn = "2022/2023",)


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repo = MatchRepository(apiService)
    }

    @Test
    fun `fetch football match list from api success`() = runBlocking {
        repo.invoke()
        val response = Resource.Success(competitionsModel)
        val channel = Channel<Resource<CompetitionsModel>>()
        val flow = channel.consumeAsFlow()
        Mockito.`when`(repo.invoke()).thenReturn(flow)
        launch {
            channel.send(response)
        }
        assertEquals(competitionsModel, response.data)
    }

}