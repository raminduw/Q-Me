package com.ramindu.weeraman.domain.validator

import com.ramindu.weeraman.domain.LOGIN_INPUT_ERROR
import com.ramindu.weeraman.domain.NO_ERROR_CODE
import com.ramindu.weeraman.domain.entities.RegisterUser
import javax.inject.Inject

class RegisterValidatorImpl @Inject constructor() : InputValidator<RegisterUser> {
    override fun validate(model: RegisterUser): Pair<Boolean, Int> {
        if (model.name.isEmpty()) {
            return Pair(false, LOGIN_INPUT_ERROR)
        }

        if (model.password.isEmpty()) {
            return Pair(false, LOGIN_INPUT_ERROR)
        }

        if (model.confirmPassword.isEmpty()) {
            return Pair(false, LOGIN_INPUT_ERROR)
        }

        if (model.confirmPassword != model.password) {
            return Pair(false, LOGIN_INPUT_ERROR)
        }

        return Pair(true, NO_ERROR_CODE)
    }
}