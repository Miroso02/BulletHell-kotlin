import gameElements.Cannon
import gameElements.Player
import java.awt.Graphics2D

val cannons = ArrayList<Cannon>().apply { add(Player) }
var timer = -1

fun updateScreen(g: Graphics2D) {
    try {
        cannons.forEach { it.update(g) }
        timer++
    }
    catch (e: ConcurrentModificationException) {e.printStackTrace()}
}