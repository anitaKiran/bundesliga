package com.kickbase.matches.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kickbase.matches.data.repository.MatchRepository
import com.kickbase.matches.utils.DataState
import com.kickbase.matches.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Anita Kiran on 8/27/2022.
 */
@HiltViewModel
class MatchViewModel @Inject constructor(private val repository: MatchRepository) :
    ViewModel() {

    private val _competitions = MutableStateFlow(DataState())
    val competitions: StateFlow<DataState> = _competitions

    init {
        fetchCompetitionsList()
    }

    fun fetchCompetitionsList() {
        repository().onEach {
            when (it) {
                is Resource.Loading -> {
                    _competitions.value = DataState(isLoading = true)
                }
                is Resource.Success -> {
                    _competitions.value = DataState(data = it.data)
                }
                is Resource.Error -> {
                    _competitions.value = DataState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}