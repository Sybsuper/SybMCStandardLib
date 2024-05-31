package com.sybsuper.sybmcstandardlib

import com.tcoded.folialib.wrapper.task.WrappedTask
import io.mockk.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class PlayerAPITest {
    private lateinit var player: Player

    @BeforeEach
    fun setup() {
        player = mockk(relaxed = true)

        mockkStatic(Bukkit::class)
        every { Bukkit.dispatchCommand(any(), any()) } returns true
        mockkStatic("com.sybsuper.sybmcstandardlib.SchedulerAPIKt")
    }

    @AfterEach
    fun breakDown() {
        unmockkStatic(Bukkit::class)
        unmockkStatic("com.sybsuper.sybmcstandardlib.SchedulerAPIKt")
    }

    @Test
    fun `sendError should call sendMessage with a red colored text component`() {
        // Act
        val msg = "Error message"
        player.sendError(msg)

        // Assert
        val expectedComponent = Component.text(msg, NamedTextColor.RED)
        verify { player.sendMessage(expectedComponent) }
    }

    @Test
    fun `sendSuccess should call sendMessage with a green colored text component`() {
        // Act
        val msg = "Success message"
        player.sendSuccess(msg)

        // Assert
        val expectedComponent = Component.text(msg, NamedTextColor.GREEN)
        verify { player.sendMessage(expectedComponent) }
    }

    @Test
    fun `runCommand should dispatch command without scheduling when on primary thread`() {
        // Arrange
        val cmd = "op me"
        every { Bukkit.isPrimaryThread() } returns true

        // Act
        player.runCommand(cmd)

        // Assert
        verify { Bukkit.dispatchCommand(player, cmd) }
        verify(exactly = 0) { runNextTick(any()) }
    }

    @Test
    fun `runCommand should schedule command dispatch if not on primary thread`() {
        // Arrange
        val player = mockk<Player>(relaxed = true)
        val cmd = "op me"
        every { Bukkit.isPrimaryThread() } returns false

        val taskSlot = slot<Consumer<WrappedTask>>()
        every { runNextTick(capture(taskSlot)) } answers { CompletableFuture.completedFuture(null) }

        // Act
        player.runCommand(cmd)

        // Assert
        verify { runNextTick(any()) }

        // Verify that Bukkit.dispatchCommand was not called yet
        verify(exactly = 0) { Bukkit.dispatchCommand(any(CommandSender::class), any(String::class)) }

        // Execute the captured lambda
        val task = mockk<WrappedTask>(relaxed = true)
        taskSlot.captured.accept(task)

        // Verify that it calls Bukkit.dispatchCommand
        verify { Bukkit.dispatchCommand(player, cmd) }
    }
}