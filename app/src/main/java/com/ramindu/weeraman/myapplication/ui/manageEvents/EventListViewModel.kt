package com.ramindu.weeraman.myapplication.ui.manageEvents

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.domain.usecases.GetEventsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class EventListViewModel @ViewModelInject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val eventListStatusLiveData = MutableLiveData<Boolean>()

    var _eventList = MutableLiveData<EventModel>()

    val eventsLiveData = MutableLiveData<Either<ErrorCode, List<EventModel>>>()

    fun getEventList() {
        viewModelScope.launch(dispatcher) {
            eventListStatusLiveData.postValue(true)
            eventsLiveData.postValue(getEventsUseCase.getEvents())
            eventListStatusLiveData.postValue(false)
        }
    }

    fun getEvents() {
        viewModelScope.launch(dispatcher) {
            val events: LiveData<EventModel> = getEventsUseCase.getEventsFlow().asLiveData()
            _eventList = events as MutableLiveData<EventModel>
        }
    }
}