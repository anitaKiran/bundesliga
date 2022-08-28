package com.kickbase.matches.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kickbase.matches.data.repository.MatchesRepository
import com.kickbase.matches.utils.DataState
import com.kickbase.matches.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


import javax.inject.Inject

/**
 * Created by Anita Kiran on 8/27/2022.
 */
@HiltViewModel
class MatchesViewModel @Inject constructor(private val repository: MatchesRepository) :
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
                    Log.e("viewmodel",""+it.data)
                    _competitions.value = DataState(data = it.data)
                }
                is Resource.Error -> {
                    Log.e("viewmodel", "${it.message.toString()}")
                    _competitions.value = DataState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}