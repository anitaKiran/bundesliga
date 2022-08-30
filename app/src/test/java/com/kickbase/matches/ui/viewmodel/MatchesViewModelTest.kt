package com.kickbase.matches.ui.viewmodel

import app.cash.turbine.test
import com.kickbase.matches.data.model.CompetitionsModel
import com.kickbase.matches.data.model.MatchList
import com.kickbase.matches.data.model.TeamInfo
import com.kickbase.matches.data.network.KickbaseServiceApi
import com.kickbase.matches.data.repository.MatchRepository
import com.kickbase.matches.utils.DataState
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Anita Kiran on 8/29/2022.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MatchesViewModelTest : BaseTest() {
    private lateinit var matchViewModel: MatchViewModel
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
                t2 = teamInfo
            )
        ),
        nd = 34, sn = "2022/2023",
    )


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repo = MatchRepository(apiService)
        matchViewModel = MatchViewModel(repo)
    }

    @Test
    fun `fetch football match list from api state loading`() = runBlocking {
        matchViewModel.competitions.test {
            assertEquals(DataState(isLoading = true), matchViewModel.competitions.value.isLoading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetch football match list from api state success`() = runBlocking {
        matchViewModel.competitions.test {
            assertEquals(
                DataState(data = competitionsModel),
                matchViewModel.competitions.value.data
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}
