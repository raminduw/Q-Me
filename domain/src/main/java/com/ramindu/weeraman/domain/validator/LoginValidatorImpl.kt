package com.ramindu.weeraman.domain.validator

import com.ramindu.weeraman.domain.LOGIN_INPUT_ERROR
import com.ramindu.weeraman.domain.NO_ERROR_CODE
import com.ramindu.weeraman.domain.entities.LoginUser
import javax.inject.Inject

class LoginValidatorImpl @Inject constructor() : InputValidator<LoginUser> {
    override fun validate(model: LoginUser): Pair<Boolean, Int> {
        if (model.name.isEmpty()) {
            return Pair(false, LOGIN_INPUT_ERROR)
        }

        if (model.password.isEmpty()) {
            return Pair(false, LOGIN_INPUT_ERROR)
        }
        return Pair(true, NO_ERROR_CODE)
    }
}