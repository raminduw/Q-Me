package com.ramindu.weeraman.myapplication.ui.manageEvents

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ramindu.weeraman.data.coroutines.CoroutinesDispatcherProvider
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.domain.usecases.GetEventsUseCase
import kotlinx.coroutines.launch

class EventListViewModel @ViewModelInject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    val eventListStatusLiveData = MutableLiveData<Boolean>()
    val eventsLiveData = MutableLiveData<Either<ErrorCode, List<EventModel>>>()

    fun getEventList() {
        viewModelScope.launch(coroutinesDispatcherProvider.io) {
            eventListStatusLiveData.postValue(true)
            eventsLiveData.postValue(getEventsUseCase.getEvents())
            eventListStatusLiveData.postValue(false)
        }
    }
}