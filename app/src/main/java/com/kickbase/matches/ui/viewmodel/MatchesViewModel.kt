package com.kickbase.matches.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kickbase.matches.data.repository.MatchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Anita Kiran on 8/27/2022.
 */
@HiltViewModel
class MatchesViewModel @Inject constructor(private val repository: MatchesRepository) :
    ViewModel() {


}