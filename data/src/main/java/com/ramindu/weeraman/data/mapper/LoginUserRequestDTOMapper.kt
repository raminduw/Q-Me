package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.LoginRequestDTO
import com.ramindu.weeraman.domain.entities.LoginUser
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class LoginUserRequestDTOMapper @Inject constructor() : Mapper<LoginUser, LoginRequestDTO> {
    override fun transform(model: LoginUser): LoginRequestDTO {
        return LoginRequestDTO(model.name, model.password)
    }
}