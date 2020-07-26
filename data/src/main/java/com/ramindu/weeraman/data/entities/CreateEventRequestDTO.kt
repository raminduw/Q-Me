package com.ramindu.weeraman.data.entities



data class CreateEventRequestDTO(val name: String) {
    constructor(
        name: String,
        description: String,
        clientNameMandatory: Boolean,
        isUnlimitedTime: Boolean,
        isUnlimitedNumbers: Boolean,
        isAutomaticallyCancel: Boolean,
        startTime: String,
        endTime: String,
        numberOfTickets: Int
    ) : this(name)
}
