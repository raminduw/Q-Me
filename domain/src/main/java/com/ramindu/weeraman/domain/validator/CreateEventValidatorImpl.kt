package com.ramindu.weeraman.domain.validator

import com.ramindu.weeraman.domain.*
import com.ramindu.weeraman.domain.entities.CreateEventModel
import javax.inject.Inject

class CreateEventValidatorImpl  @Inject constructor() :InputValidator<CreateEventModel> {

    override fun validate(model: CreateEventModel): Pair<Boolean, Int>{
        if (model.name.isEmpty()) {
            return Pair(false, INVALID_INPUT_NAME)
        }

        if (model.description.isEmpty()) {
            return Pair(false, INVALID_INPUT_DESCRIPTION)
        }

        if (!model.isUnlimitedTime) {
            if (model.startTime != null && model.endTime != null) {
                if (model.endTime.before(model.startTime)) {
                    return Pair(false, INVALID_INPUT_DATE_TIME)
                }
            }
        }

        if (!model.isUnlimitedNumbers) {
            if (model.numberOfTickets.isNullOrEmpty()) {
                return Pair(false, INVALID_TICKET_COUNT)
            }
        }

        return Pair(true, NO_ERROR_CODE)
    }

}