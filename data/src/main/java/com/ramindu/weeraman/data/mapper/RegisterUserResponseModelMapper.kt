package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.RegisterResponseDTO
import com.ramindu.weeraman.domain.entities.RegisterResult
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class RegisterUserResponseModelMapper @Inject constructor() : Mapper<RegisterResponseDTO, RegisterResult> {
    override fun transform(model: RegisterResponseDTO): RegisterResult {
        return RegisterResult(model.name, true)
    }
}