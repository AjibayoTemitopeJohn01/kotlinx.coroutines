/*
 * Copyright 2016-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.coroutines.channels

import kotlinx.coroutines.*
import kotlin.test.*


class ChannelFactoryTest : TestBase() {

    @Test
    fun testRendezvousChannel() {
        assertTrue(Channel<Int>() is BufferedChannel)
        assertTrue(Channel<Int>(0) is BufferedChannel)
    }

    @Test
    fun testLinkedListChannel() {
        assertTrue(Channel<Int>(Channel.UNLIMITED) is BufferedChannel)
    }

    @Test
    fun testConflatedChannel() {
        assertTrue(Channel<Int>(Channel.CONFLATED) is ConflatedChannel)
    }

    @Test
    fun testArrayChannel() {
        assertTrue(Channel<Int>(1) is BufferedChannel)
        assertTrue(Channel<Int>(10) is BufferedChannel)
    }

    @Test
    fun testInvalidCapacityNotSupported() = runTest({ it is IllegalArgumentException }) {
        Channel<Int>(-3)
    }
}
