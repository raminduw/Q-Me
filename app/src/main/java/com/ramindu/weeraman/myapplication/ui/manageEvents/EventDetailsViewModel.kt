package com.ramindu.weeraman.myapplication.ui.manageEvents

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ramindu.weeraman.data.coroutines.CoroutinesDispatcherProvider
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventDetailModel
import com.ramindu.weeraman.domain.usecases.GetEventByIdUseCase
import kotlinx.coroutines.launch


class EventDetailsViewModel @ViewModelInject constructor(
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    val eventDetailsStatusLiveData = MutableLiveData<Boolean>()
    val eventLiveData = MutableLiveData<Either<ErrorCode, EventDetailModel>>()

    fun getEventDetails(eventCode: String) {
        viewModelScope.launch(coroutinesDispatcherProvider.io) {
            eventDetailsStatusLiveData.postValue(true)
            eventLiveData.postValue(getEventByIdUseCase.getEventDetailsById(eventId = eventCode))
            eventDetailsStatusLiveData.postValue(false)
        }
    }
}