package com.sybsuper.sybmcstandardlib.event

import com.sybsuper.sybmcstandardlib.SybMCStandardLib
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin


/**
 * Register an event listener.
 * @param handler The handler to run when the event is called.
 * @sample samples.welcomePlayer
 */
inline fun <reified T : Event> on(
    priority: EventPriority = EventPriority.NORMAL,
    plugin: JavaPlugin = SybMCStandardLib.INSTANCE,
    ignoreCanceled: Boolean = true,
    crossinline handler: (T) -> Unit
) {
    Bukkit.getPluginManager().registerEvent(
        T::class.java, object : Listener {}, priority,
        { _, event ->
            handler(event as T)
        }, plugin, ignoreCanceled
    )
}
