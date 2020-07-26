package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.GetEventDetailsResponseDTO
import com.ramindu.weeraman.domain.entities.EventDetailModel
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class GetEventDetailsResponseModelMapper @Inject constructor() : Mapper<GetEventDetailsResponseDTO, EventDetailModel> {
    override fun transform(model: GetEventDetailsResponseDTO): EventDetailModel {
        return EventDetailModel("ABC 123", "tittle", "description")
    }
}