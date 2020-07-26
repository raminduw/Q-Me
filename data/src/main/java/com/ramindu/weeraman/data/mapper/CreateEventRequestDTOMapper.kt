package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.CreateEventRequestDTO
import com.ramindu.weeraman.domain.entities.CreateEventModel
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class CreateEventRequestDTOMapper @Inject constructor() : Mapper<CreateEventModel, CreateEventRequestDTO> {
    override fun transform(model: CreateEventModel): CreateEventRequestDTO {
        val startDate = if (model.isUnlimitedTime) "" else model.startTime.toString()
        val endDate = if (model.isUnlimitedTime) "" else model.endTime.toString()
        val ticketCount = if (model.isUnlimitedNumbers) 0 else model.numberOfTickets?.toInt() ?: 0

        CreateEventRequestDTO(
            model.name, model.description, model.isClientNameMandatory, model.isUnlimitedTime, model.isUnlimitedNumbers, false,
            startDate, endDate, ticketCount
        )

        return CreateEventRequestDTO("Berlin")
    }
}