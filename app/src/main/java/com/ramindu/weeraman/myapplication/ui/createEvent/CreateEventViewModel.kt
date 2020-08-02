package com.ramindu.weeraman.myapplication.ui.createEvent

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ramindu.weeraman.data.coroutines.CoroutinesDispatcherProvider
import com.ramindu.weeraman.domain.entities.CreateEventModel
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.domain.usecases.GenerateEventUseCase
import com.ramindu.weeraman.myapplication.DATE_TIME_FORMAT
import com.ramindu.weeraman.myapplication.EMPTY_SPACE
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateEventViewModel @ViewModelInject constructor(
    private val generateEventUseCase: GenerateEventUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) :
    ViewModel() {
    val createEventStatus = MutableLiveData<Either<ErrorCode, EventModel>>()
    private var startDate: Date? = null
    private var endDate: Date? = null
    val startDateLiveData = MutableLiveData<String>()
    val endDateLiveData = MutableLiveData<String>()
    val createEventProgressStatusLiveData = MutableLiveData<Boolean>()

    fun createEvent(
        name: String, description: String, clientNameMandatory: Boolean, isUnlimitedTime: Boolean, isUnlimitedNumbers: Boolean,
        isAutomaticallyCancel: Boolean, numberOfTickets: String?
    ) {
        val createEventModel = CreateEventModel(
            name = name,
            description = description,
            isClientNameMandatory = clientNameMandatory,
            isUnlimitedTime = isUnlimitedTime,
            isUnlimitedNumbers = isUnlimitedNumbers,
            isAutomaticallyCancel = isAutomaticallyCancel,
            startTime = startDate,
            endTime = endDate,
            numberOfTickets = numberOfTickets
        )
        viewModelScope.launch(coroutinesDispatcherProvider.io) {
            createEventProgressStatusLiveData.postValue(true)
            createEventStatus.postValue(generateEventUseCase.createEvent(createEventModel))
            createEventProgressStatusLiveData.postValue(false)
        }
    }

    fun setEventDate(selectedYear: Int, selectedMonth: Int, selectedDay: Int, selectedHour: Int, selectedMinute: Int, selectingStartDate: Boolean) {
        val date = GregorianCalendar(
            selectedYear,
            selectedMonth,
            selectedDay,
            selectedHour,
            selectedMinute
        ).time
        if (selectingStartDate) {
            startDate = date
            val sdf = SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH)
            startDateLiveData.postValue(sdf.format(startDate))
        } else {
            endDate = date
            val sdf = SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH)
            endDateLiveData.postValue(sdf.format(endDate))
        }
    }

    fun resetEventDate() {
        startDate = null
        endDate = null
        endDateLiveData.postValue(EMPTY_SPACE)
        startDateLiveData.postValue(EMPTY_SPACE)
    }

}