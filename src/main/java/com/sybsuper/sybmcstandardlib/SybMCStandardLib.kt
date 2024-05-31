package com.sybsuper.sybmcstandardlib

import com.tcoded.folialib.FoliaLib
import org.bukkit.plugin.java.JavaPlugin

class SybMCStandardLib : JavaPlugin() {
    companion object {
        lateinit var scheduler: FoliaLib
        lateinit var INSTANCE: SybMCStandardLib
        fun foliaServer(): Boolean = scheduler.isFolia
    }

    override fun onEnable() {
        INSTANCE = this
        scheduler = FoliaLib(this)
    }

    override fun onDisable() {
    }
}
