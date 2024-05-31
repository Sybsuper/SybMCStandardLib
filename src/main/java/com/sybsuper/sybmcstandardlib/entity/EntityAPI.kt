package com.sybsuper.sybmcstandardlib.entity

import com.sybsuper.sybmcstandardlib.SybMCStandardLib
import org.bukkit.Location
import org.bukkit.entity.LivingEntity
import java.util.concurrent.CompletableFuture

/**
 * Teleports the entity to the given location.
 * Uses [LivingEntity.teleportAsync] on Folia servers, and [LivingEntity.teleport] on non-Folia servers.
 * @return A [CompletableFuture] that completes when the teleportation is done. Which is instantly when ran on non-Folia servers.
 */
fun LivingEntity.teleportSafe(location: Location): CompletableFuture<Boolean> {
    if (SybMCStandardLib.foliaServer()) {
        return this.teleportAsync(location)
    } else {
        this.teleport(location)
        return CompletableFuture.completedFuture(true)
    }
}