package com.ramindu.weeraman.myapplication

import com.ramindu.weeraman.data.coroutines.CoroutinesDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
fun provideFakeCoroutinesDispatcherProvider(
    dispatcher: TestCoroutineDispatcher?
): CoroutinesDispatcherProvider {
    val sharedTestCoroutineDispatcher = TestCoroutineDispatcher()
    return CoroutinesDispatcherProvider(
        dispatcher ?: sharedTestCoroutineDispatcher,
        dispatcher ?: sharedTestCoroutineDispatcher,
        dispatcher ?: sharedTestCoroutineDispatcher)
}