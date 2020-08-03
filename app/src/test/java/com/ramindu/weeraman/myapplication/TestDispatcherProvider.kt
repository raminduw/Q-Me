package com.ramindu.weeraman.myapplication

import com.ramindu.weeraman.domain.dispacther.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestDispatcherProvider (
    val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
): DispatcherProvider {
    override fun default(): CoroutineDispatcher =
        dispatcher

    override fun main(): CoroutineDispatcher =
        dispatcher

    override fun io(): CoroutineDispatcher =
        dispatcher

    override fun unconfined(): CoroutineDispatcher =
        dispatcher
}