import gameElements.Cannon
import gameElements.Player
import java.awt.Graphics2D

val player = Player()
val cannons = ArrayList<Cannon>().apply { add(player) }
var timer = -1

fun updateScreen(g: Graphics2D) {
    try {
        cannons.forEach { it.update(g) }
        timer++
    }
    catch (e: ConcurrentModificationException) {e.printStackTrace()}
}