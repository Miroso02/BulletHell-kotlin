import gameElements.*
import gameElements.patterns.*
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

fun initCannons() {
    fun pointAround(point: Point, dist: Float, density: Int, i: Int = 0): Point =
            point + Point(cos(i * 2 * Math.PI / density), sin(i * 2 * Math.PI / density)) * dist

    val c = Cannon(600, 150)
    c.health = 800

    val mp1 = BehaviorPattern<MoveComponent>()
            .then(1) { (obj) ->
                obj.position = c.position
                obj.size = 8
                obj.color = Color.BLUE
            }
            .then(moveTo(60) { (obj) ->
                pointAround(c.position, 100f, 50, obj.index)
            })
            .then(120, stand)
            .then(1) { (obj) ->
                obj.color = Color.GREEN
            }
            .then(moveTo(120) {
                Player.position
            })
            .then(1) { (obj) ->
                obj.size = 15
            }
            .then(60, stand)

    val cbMovePattern = BehaviorPattern<MoveComponent>()
            .then(mp1)
            .then(1) { context ->
                val (obj) = context
                context.velocity = Point.randVector() * (Random.nextFloat() * 2f + 1f)
                obj.color = Color.MAGENTA
                obj.size = 8
            }
//            .then(160, moveForward)
//            .then(1) { obj ->
//                obj.createTime = timer
//                obj.curMovFuncInd = 0
//            }
            .then(2000, moveForward)
    val cannonFirePattern = LoopedBehaviorPattern<SimplePatternComponent>()
            .then(1) { (can) ->
                can as Cannon
                val bullets = List(50) { Bullet(index = it) }
                        .onEach { it.behaviors.add(MoveComponent(it, cbMovePattern)) }
                can.bullets.addAll(bullets)
            }
            .then(29, stand)
            .repeat(7)
            .then(790, stand)
    c.behaviors.add(SimplePatternComponent(c, cannonFirePattern))
    cannons.add(c)
}