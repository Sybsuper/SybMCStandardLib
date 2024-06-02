package com.sybsuper.sybmcstandardlib.event

import io.mockk.*
import org.bukkit.Bukkit
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EventAPITest {
    private lateinit var pluginManager: PluginManager
    private val plugin: JavaPlugin = mockk()

    @BeforeEach
    fun setUp() {
        pluginManager = mockk()
        every { pluginManager.registerEvent(any(), any(), any(), any(), any(), any()) } returns Unit

        mockkStatic(Bukkit::class)
        every { Bukkit.getPluginManager() } returns pluginManager
    }

    @AfterEach
    fun tearDown() {
        unmockkStatic(Bukkit::class)
    }

    @Test
    fun `on registers event with default parameters`() {
        on<PlayerJoinEvent>(plugin = plugin) { }

        verify {
            pluginManager.registerEvent(
                PlayerJoinEvent::class.java,
                any(),
                EventPriority.NORMAL,
                any(),
                plugin,
                true
            )
        }
    }
}