package com.ramindu.weeraman.domain.validator

interface InputValidator<T> {

    fun validate(model: T): Pair<Boolean, Int>
}