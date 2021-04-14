import gameElements.*
import gameElements.patterns.LoopedMovePattern
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
                obj.position = c.position
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
//            .then(160, moveForward)
//            .then(1) { obj ->
//                obj.createTime = timer
//                obj.curMovFuncInd = 0
//            }
            .then(2000, moveForward)

    val cannonFirePattern = LoopedMovePattern()
            .then(1) { can ->
                can as Cannon
                val bullets = List(50) { Bullet(it) }
                        .onEach { it.movePattern = cbMovePattern }
                can.bullets.addAll(bullets)
            }
            .then(29, stand)
            .repeat(7)
            .then(790, stand)
    c.movePattern = cannonFirePattern
    cannons.add(c)
}