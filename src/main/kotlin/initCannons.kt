import gameElements.*
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin

fun initCannons() {
    val c = Cannon(600, 150)
    c.health = 300

    val cbMovePattern = LoopedMovePattern()
            .then(1) { obj -> obj.size = 8; obj.color = Color.BLUE }
            .then(moveTo(60) { _, ind ->
                c.position + Point(cos(ind * 2 * Math.PI / 50), sin(ind * 2 * Math.PI / 50)) * 100f
            })
            .then(120, stand)
            .then(1) { obj ->
                obj.color = Color.GREEN
            }
            .then(moveTo(120) { _, _ -> Player.position })
            .then(1) { obj ->
                obj.size = 15
            }
            .then(60, stand)
            .then(1) { obj ->
                obj.velocity = Point.randVector() * 3f
                obj.color = Color.RED
                obj.size = 8
            }
            .then(200, moveForward)
    val pattern = FirePattern { List(50) { Bullet(it) } }
            .applyEvery { if (timer % 20 == 0 && timer % 360 < 100) it else emptyList() }
            .apply { it.position = c.position }
            .apply { it.size = 8; it.color = Color.BLUE }
            .apply { it.movePattern = cbMovePattern }
    c.addFirePatterns(pattern)
    cannons.add(c)
}