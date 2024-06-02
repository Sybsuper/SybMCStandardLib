package samples

import com.sybsuper.sybmcstandardlib.event.on
import org.bukkit.event.player.PlayerJoinEvent

fun welcomePlayer() {
    on<PlayerJoinEvent> { event ->
        event.player.sendMessage("Welcome to the server!!!")
    }
}