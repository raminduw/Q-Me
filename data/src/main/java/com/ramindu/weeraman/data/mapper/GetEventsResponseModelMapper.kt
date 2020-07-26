package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.GetEventsResponseDTO
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class GetEventsResponseModelMapper @Inject constructor() : Mapper<GetEventsResponseDTO, MutableList<EventModel>> {
    override fun transform(model: GetEventsResponseDTO): MutableList<EventModel> {
        return mutableListOf(EventModel("","",""))
    }
}