package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.CreateEventResponseDTO
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class CreateEventResponseModelMapper @Inject constructor() : Mapper<CreateEventResponseDTO , EventModel> {
    override fun transform(model: CreateEventResponseDTO): EventModel {
        return EventModel("Berlin","","")
    }

}