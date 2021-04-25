import gameElements.*
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.behaviorPattern.LoopedBehaviorPattern
import gameElements.components.patternComponents.BulletControlComponent
import gameElements.components.patternComponents.DisplayComponent
import gameElements.components.patternComponents.MoveComponent
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

fun initCannons() {
    fun pointAround(point: Point, dist: Float, density: Int, i: Int = 0): Point =
        point + Point(cos(i * 2 * Math.PI / density), sin(i * 2 * Math.PI / density)) * dist

    val c = Cannon()
    c.body.position = Point(600, 150)
    c.health = 800

    val cbMovePattern = BehaviorPattern<MoveComponent>()
        .then(1) { context ->
            context.position = c.body.position
            context.size = 8
        }
        .then(moveTo(60) { context ->
            pointAround(c.body.position, 100f, 50, context.index)
        })
        .then(120, stand)
        .then(moveTo(121) {
            Player.body.position
        })
        .then(1) { context ->
            context.size = 15
        }
        .then(60, stand)
        .then(1) { context ->
            context.velocity = Point.randVector() * (Random.nextFloat() * 2f + 1f)
            context.size = 8
        }
        .then(2000, moveForward)
    val cbColorPattern = BehaviorPattern<DisplayComponent>()
        .then(1) { it.color = Color.BLUE }
        .then(180, stand)
        .then(1) { it.color = Color.GREEN }
        .then(181, stand)
        .then(1) { it.color = Color.MAGENTA }
        .then(2000, stand)

    val cannonFirePattern = LoopedBehaviorPattern<BulletControlComponent>()
        .then(1) { context ->
            val bullets = List(50) { Bullet(index = it) }
                .onEachIndexed { i, b ->
                    b.behaviors.add(MoveComponent(cbMovePattern, b.body, i))
                    b.behaviors.add(DisplayComponent(cbColorPattern, b.body, b.color, i))
                }
            context.bullets.addAll(bullets)
        }
        .then(29, stand)
        .repeat(7)
        .then(790, stand)
    c.behaviors.add(BulletControlComponent(cannonFirePattern, c.bullets))
    cannons.add(c)
}