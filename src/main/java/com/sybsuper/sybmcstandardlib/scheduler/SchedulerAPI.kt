package com.sybsuper.sybmcstandardlib.scheduler

import com.sybsuper.sybmcstandardlib.SybMCStandardLib
import com.tcoded.folialib.wrapper.task.WrappedTask
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

/**
 * Runs the given function on the next tick.
 * Use for: world day time, world game time, weather cycle, sleep night skipping, executing command
 *
 * @param func The function to run on the next tick.
 * @return A CompletableFuture that completes when the function has been run.
 */
fun runNextTick(func: Consumer<WrappedTask>): CompletableFuture<Void> =
    SybMCStandardLib.scheduler.impl.runNextTick(func)

/**
 * Runs the given function after a delay.
 *
 * @param delayTicks The delay before the function is run, in ticks.
 * @param func The function to run after the delay.
 */
fun runLater(delayTicks: Long, func: Consumer<WrappedTask>) =
    SybMCStandardLib.scheduler.impl.runLater(func, delayTicks)

/**
 * Runs the given function repeatedly.
 *
 * @param delayTicks The delay before the function is first run, in ticks.
 * @param intervalTicks The interval between subsequent runs, in ticks.
 * @param func The function to run repeatedly.
 */
fun runTimer(delayTicks: Long, intervalTicks: Long, func: Consumer<WrappedTask>) =
    SybMCStandardLib.scheduler.impl.runTimer(func, delayTicks, intervalTicks)

/**
 * Runs the given function asynchronously.
 *
 * @param func The function to run asynchronously.
 * @return A CompletableFuture that completes when the function has been run.
 */
fun runAsync(func: Consumer<WrappedTask>): CompletableFuture<Void> =
    SybMCStandardLib.scheduler.impl.runAsync(func)

