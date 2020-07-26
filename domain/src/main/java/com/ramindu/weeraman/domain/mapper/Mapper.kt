package com.ramindu.weeraman.domain.mapper

interface Mapper<IN, OUT> {
    fun transform(model: IN): OUT
}