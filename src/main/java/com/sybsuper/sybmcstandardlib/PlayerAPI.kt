package com.sybsuper.sybmcstandardlib

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun Player.sendError(msg: String) = this.sendMessage(Component.text(msg, NamedTextColor.RED))

fun Player.sendSuccess(msg: String) = this.sendMessage(Component.text(msg, NamedTextColor.GREEN))

/**
 * Run a command as the sender.
 * @param cmd The command to run.
 * @sample samples.sayHiToSybsuper
 */
fun CommandSender.runCommand(cmd: String) {
    if (Bukkit.isPrimaryThread()) {
        Bukkit.dispatchCommand(this, cmd)
    } else {
        val player = this
        runNextTick {
            Bukkit.dispatchCommand(player, cmd)
        }
    }
}
