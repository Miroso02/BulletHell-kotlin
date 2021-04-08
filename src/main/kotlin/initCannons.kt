import gameElements.*
import gameElements.patterns.FirePattern
import gameElements.patterns.MovePattern
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

fun initCannons() {
    fun pointAround(point: Point, dist: Float, density: Int, i: Int = 0) =
            point + Point(cos(i * 2 * Math.PI / density), sin(i * 2 * Math.PI / density)) * dist

    val c = Cannon(600, 150)
    c.health = 800

    val mp1 = MovePattern()
            .then(1) { obj ->
                obj.size = 8
                obj.color = Color.BLUE
            }
            .then(moveTo(60) { _, ind ->
                pointAround(c.position, 100f, 50, ind)
            })
            .then(120, stand)
            .then(1) { obj ->
                obj.color = Color.GREEN
            }
            .then(moveTo(120) { _, _ ->
                Player.position
            })
            .then(1) { obj ->
                obj.size = 15
            }
            .then(60, stand)

    val cbMovePattern = MovePattern()
            .then(mp1)
            .then(1) { obj ->
                obj.velocity = Point.randVector() * (Random.nextFloat() * 2f + 1f)
                obj.color = Color.MAGENTA
                obj.size = 8
            }
            .then(160, moveForward)
            .then(1) { obj ->
                obj.color = Color.ORANGE
            }
            .then(80, stand)
            .then(mp1)
            .then(1) { obj ->
                obj.velocity = Point.randVector() * (Random.nextFloat() * 2.5f + 1.5f)
                obj.color = Color.RED
                obj.size = 8
            }
            .then(1000, moveForward)

    val pattern = FirePattern { List(50) { Bullet(it) } }
            .applyEvery { if (timer % 30 == 0 && timer % 1000 < 210) it else emptyList() }
            .apply { it.position = c.position }
            .apply { it.size = 8; it.color = Color.BLUE }
            .apply { it.movePattern = cbMovePattern }
    c.addFirePatterns(pattern)
    cannons.add(c)
}