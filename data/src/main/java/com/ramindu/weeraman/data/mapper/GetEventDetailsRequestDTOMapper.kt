package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.GetEventDetailsRequestDTO
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class GetEventDetailsRequestDTOMapper @Inject constructor() : Mapper<String, GetEventDetailsRequestDTO> {
    override fun transform(model: String): GetEventDetailsRequestDTO {
        return GetEventDetailsRequestDTO(model)
    }
}