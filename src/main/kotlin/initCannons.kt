import gameElements.*
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

fun initCannons() {
    val c = Cannon(600, 150)
    c.health = 1000

    val pattern = FirePattern { List(50) { Bullet() } }
            .applyEvery { if (timer % 20 == 0 && timer % 360 < 100) it else emptyList() }
            .applyIndexed { i, b -> b.position = c.position }
            .apply { it.size = 8; it.color = Color.BLUE }
            .applyIndexed { i, b ->
                b.velocity = Point(5, 2)
                b.movePattern = MovePattern()
                    .then(moveTo({ c.position + Point(cos(i * 2 * Math.PI / 50), sin(i * 2 * Math.PI / 50)) * 100f }, 60))
                    .then(120, stand)
                    .then(1) { obj -> obj.color = Color.GREEN }
                    .then(moveTo({ player.position }, 120))
                    .then(1) { obj -> obj.size = 15 }
                    .then(60, stand)
                    .then(1) { obj -> obj.velocity = Point.randVector() * 3f; obj.color = Color.RED; obj.size = 8 }
                    .then(1000, moveForward)
            }

    c.addFirePatterns(pattern)
    cannons.add(c)
}