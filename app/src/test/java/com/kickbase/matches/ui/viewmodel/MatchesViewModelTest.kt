package com.kickbase.matches.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.kickbase.matches.data.model.CompetitionsModel
import com.kickbase.matches.data.model.MatchList
import com.kickbase.matches.data.model.TeamInfo
import com.kickbase.matches.data.network.KickbaseServiceApi
import com.kickbase.matches.data.repository.MatchRepository
import com.kickbase.matches.utils.DataState
import com.kickbase.matches.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
 * Created by Anita Kiran on 8/29/2022.
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MatchesViewModelTest{


    private lateinit var matchViewModel: MatchViewModel
    //@Mock
    private lateinit var repo: MatchRepository
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    val teamInfo = TeamInfo(goals =0, clubId = "21", clubName = "Braunschw.",p=0, clubShortName = "FWS", sp = 2740)

    private val competitionsModel = CompetitionsModel(1,
        listOf(MatchList("2022-09-02T16:30:00Z", i= "6456",mt=1, statusOfMatch =0, t1=teamInfo, t2 = teamInfo)),
        nd=34,sn="2022/2023",)

    @Mock
    lateinit var apiService: KickbaseServiceApi

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        repo = MatchRepository(apiService)
        matchViewModel = MatchViewModel(repo)
    }

    @Test
    fun `fetch football match list from api Success`() = runBlocking {
        //delay(5000)
        matchViewModel.competitions.test {
            assertEquals(DataState(isLoading = true), matchViewModel.competitions.value.isLoading)
            cancelAndIgnoreRemainingEvents()
        }


      /*  val response = Resource.Success(competitionsModel)
        val channel = Channel<Resource<CompetitionsModel>>()
        val flow = channel.consumeAsFlow()
        Mockito.`when`(repo.invoke()).thenReturn(flow)
        launch {
            channel.send(response)
        }
        matchViewModel.fetchCompetitionsList()
        //assertEquals(competitionsModel, matchViewModel.competitions.value.data)
        //Assert.assertEquals(false, employeeViewModel.fetchLoadStatus()?.value)
        //assertEquals(DataState(isLoading = true), matchViewModel.competitions.value) */
    }



    /*
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var matchViewModel: MatchViewModel
    private lateinit var repository: MatchRepository
    val teamInfo = TeamInfo(g=0, clubId = "21", clubName = "Braunschw.",p=0, clubShortName = "FWS", sp = 2740)

    private val competitionsModel = CompetitionsModel(1,
        listOf(MatchList("2022-09-02T16:30:00Z", i= "6456",mt=1,s=0, t1=teamInfo, t2 = teamInfo)),
        nd=34,sn="2022/2023",)

    val stateFlow = MutableStateFlow<DataState>(DataState(data= competitionsModel))


    @Mock
    lateinit var apiService: KickbaseServiceApi

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainRule = TestCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        repository = MatchRepository(apiService)
        matchViewModel = MatchViewModel(repository)
    }


    @Test
    fun `fetch football match list from api Success`() = runBlocking {
//        matchViewModel.fetchCompetitionsList()
        matchViewModel.competitions.test {
            delay(5000)
            assertEquals(competitionsModel, awaitComplete().equals(competitionsModel))
            cancelAndIgnoreRemainingEvents()
        }
    } */
}