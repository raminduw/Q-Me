package com.ramindu.weeraman.domain.entities

import java.util.*

data class CreateEventModel(
    val name: String,
    val description: String,
    val isClientNameMandatory: Boolean,
    val isUnlimitedTime: Boolean,
    val isUnlimitedNumbers: Boolean,
    val isAutomaticallyCancel: Boolean,
    val startTime: Date?,
    val endTime: Date?,
    val numberOfTickets: String?
)