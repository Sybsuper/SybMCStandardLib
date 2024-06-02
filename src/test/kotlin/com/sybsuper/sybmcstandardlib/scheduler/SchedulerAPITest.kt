package com.sybsuper.sybmcstandardlib.scheduler

import com.sybsuper.sybmcstandardlib.SybMCStandardLib
import com.tcoded.folialib.FoliaLib
import com.tcoded.folialib.impl.ServerImplementation
import com.tcoded.folialib.wrapper.task.WrappedTask
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class SchedulerAPITest {
    private lateinit var scheduler: FoliaLib
    private lateinit var implementation: ServerImplementation

    @BeforeEach
    fun setUp() {
        scheduler = mockk<FoliaLib>()
        implementation = mockk<ServerImplementation>()

        every { scheduler.impl } returns implementation

        mockkStatic(SybMCStandardLib::class)
        SybMCStandardLib.scheduler = scheduler
    }

    @Test
    fun `runNextTick should call runNextTick on the scheduler`() {
        val func = mockk<Consumer<WrappedTask>>()
        val future = CompletableFuture<Void>()

        every { implementation.runNextTick(func) } returns future

        runNextTick(func)

        verify { implementation.runNextTick(func) }
    }

    @Test
    fun `runLater should call runLater on the scheduler`() {
        val func = mockk<Consumer<WrappedTask>>()
        val delayTicks = 10L

        every { implementation.runLater(func, delayTicks) } returns mockk()

        runLater(delayTicks, func)

        verify { implementation.runLater(func, delayTicks) }
    }

    @Test
    fun `runTimer should call runTimer on the scheduler`() {
        val func = mockk<Consumer<WrappedTask>>()
        val delayTicks = 10L
        val intervalTicks = 20L

        every { implementation.runTimer(func, delayTicks, intervalTicks) } returns mockk()

        runTimer(delayTicks, intervalTicks, func)

        verify { implementation.runTimer(func, delayTicks, intervalTicks) }
    }

    @Test
    fun `runAsync should call runAsync on the scheduler`() {
        val func = mockk<Consumer<WrappedTask>>()
        val future = CompletableFuture<Void>()

        every { implementation.runAsync(func) } returns future

        runAsync(func)

        verify { implementation.runAsync(func) }
    }
}