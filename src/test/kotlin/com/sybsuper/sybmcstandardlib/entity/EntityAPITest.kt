package com.sybsuper.sybmcstandardlib.entity

import com.sybsuper.sybmcstandardlib.SybMCStandardLib
import com.tcoded.folialib.FoliaLib
import io.mockk.*
import org.bukkit.Location
import org.bukkit.entity.LivingEntity
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EntityAPITest {
    private lateinit var entity: LivingEntity
    private lateinit var mockScheduler: FoliaLib

    @BeforeEach
    fun setup() {
        entity = mockk()
        every { entity.teleport(any(Location::class)) } returns true
        every { entity.teleportAsync(any(Location::class)) } returns mockk()

        mockScheduler = mockk<FoliaLib>()

        mockkStatic(SybMCStandardLib::class)
        SybMCStandardLib.scheduler = mockScheduler
    }

    @AfterEach
    fun breakdown() {
        unmockkStatic(SybMCStandardLib::class)
    }

    @Test
    fun `teleportSafe uses teleportAsync on Folia servers`() {
        // Arrange
        every { mockScheduler.isFolia } returns true
        val loc = mockk<Location>()

        // Act
        entity.teleportSafe(loc)

        // Assert
        verify { entity.teleportAsync(loc) }
        verify(exactly = 0) { entity.teleport(any(loc::class)) }
    }

    @Test
    fun `teleportSafe uses teleport on non-Folia servers`() {
        // Arrange
        every { mockScheduler.isFolia } returns false
        val loc = mockk<Location>()

        // Act
        entity.teleportSafe(loc)

        // Assert
        verify { entity.teleport(loc) }
        verify(exactly = 0) { entity.teleportAsync(any(loc::class)) }
    }
}