package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.RegisterRequestDTO
import com.ramindu.weeraman.domain.entities.RegisterUser
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class RegisterUserRequestDTOMapper @Inject constructor() : Mapper<RegisterUser, RegisterRequestDTO> {
    override fun transform(model: RegisterUser): RegisterRequestDTO {
        return RegisterRequestDTO(model.name, model.password)
    }
}