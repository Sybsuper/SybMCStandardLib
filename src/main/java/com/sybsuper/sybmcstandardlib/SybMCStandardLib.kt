package com.sybsuper.sybmcstandardlib

import com.tcoded.folialib.FoliaLib
import org.bukkit.plugin.java.JavaPlugin

class SybMCStandardLib : JavaPlugin() {
    companion object {
        @JvmStatic
        lateinit var scheduler: FoliaLib
        @JvmStatic
        lateinit var INSTANCE: SybMCStandardLib
        @JvmStatic
        fun foliaServer(): Boolean = scheduler.isFolia
    }

    override fun onEnable() {
        INSTANCE = this
        scheduler = FoliaLib(this)
    }

    override fun onDisable() {
    }
}
