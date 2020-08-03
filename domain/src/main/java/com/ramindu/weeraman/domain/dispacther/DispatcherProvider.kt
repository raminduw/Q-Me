package com.ramindu.weeraman.domain.dispacther

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    fun default() : CoroutineDispatcher
    fun main() : CoroutineDispatcher
    fun io() : CoroutineDispatcher
    fun unconfined() : CoroutineDispatcher
}