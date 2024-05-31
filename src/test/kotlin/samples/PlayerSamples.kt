package samples

import com.sybsuper.sybmcstandardlib.runCommand
import org.bukkit.entity.Player

fun sayHiToSybsuper(player: Player) {
    player.runCommand("msg Sybsuper Hi!")
}