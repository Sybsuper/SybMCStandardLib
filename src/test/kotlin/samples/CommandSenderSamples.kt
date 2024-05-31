@file:Suppress("unused")

package samples

import com.sybsuper.sybmcstandardlib.commandsender.runCommand
import org.bukkit.Bukkit

fun sayHiToSybsuper() {
    Bukkit.getConsoleSender().runCommand("msg Sybsuper Hi!")
}