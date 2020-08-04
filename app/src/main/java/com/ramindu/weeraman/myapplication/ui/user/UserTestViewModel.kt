package com.ramindu.weeraman.myapplication.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramindu.weeraman.domain.usecases.UserTestUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UserTestViewModel @ViewModelInject constructor(
    private val userTestUseCase: UserTestUseCase,
    private val dispatcher: CoroutineDispatcher

) : ViewModel() {

    val loginStatusLiveData = MutableLiveData<Boolean>()
    val loginResultLiveData = MutableLiveData<Int>()

    fun userLogin() {
        loginStatusLiveData.postValue(true)

        viewModelScope.launch(dispatcher) {
            loginResultLiveData.postValue(userTestUseCase.getValue())
        }

        loginStatusLiveData.postValue(false)
    }
}


/*
class PostViewModel(private val redditRepository: RedditRepository) : ViewModel() {

    val stateData = MutableLiveData<State>()

    fun getRedditPost() {
        viewModelScope.launch {
            stateData.value = State.Loading
            val post: State.Post = redditRepository.getPost()
            stateData.value = post
        }
    }
}

sealed class State {
    object Loading : State()
    data class Post(val text: String) : State()
}
 */

