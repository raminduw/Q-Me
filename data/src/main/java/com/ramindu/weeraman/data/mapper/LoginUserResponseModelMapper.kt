package com.ramindu.weeraman.data.mapper

import com.ramindu.weeraman.data.entities.LoginResponseDTO
import com.ramindu.weeraman.domain.entities.LoginResult
import com.ramindu.weeraman.domain.mapper.Mapper
import javax.inject.Inject

class LoginUserResponseModelMapper @Inject constructor() : Mapper<LoginResponseDTO, LoginResult> {
    override fun transform(model: LoginResponseDTO): LoginResult {
        return LoginResult(model.name, true)
    }
}