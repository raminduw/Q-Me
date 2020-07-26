package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.GetEventsRequestDTO
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class GetEventsRequestDTOMapper @Inject constructor() : Mapper<String, GetEventsRequestDTO> {
    override fun transform(model: String): GetEventsRequestDTO {
        return GetEventsRequestDTO(model)
    }
}