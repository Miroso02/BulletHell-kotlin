import gameElements.*
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.*
import gameElements.elements.BodyElement
import gameElements.patterns.moveForward
import gameElements.patterns.moveTo
import gameElements.patterns.stand
import java.awt.Color
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.random.Random

fun initCannons() {
    fun pointAround(point: Point, dist: Float, density: Int, i: Int = 0): Point =
        point + Point(cos(i * 2 * Math.PI / density), sin(i * 2 * Math.PI / density)) * dist

    val c = Cannon()
    c.body.position = Point(600, 150)
    c.healthElement.health = 800

    val cbMovePattern = BehaviorPattern<UniversalPatternComponent>()
        .then(1) { (context) ->
            val body: BodyElement by context
            body.position = c.body.position
            body.size = 8
        }
        .then(moveTo(60) { context ->
            pointAround(c.body.position, 100f, 50, context.index)
        })
        .then(120, stand)
        .then(moveTo(121) {
            Player.body.position
        })
        .then(1) { (context) ->
            val body: BodyElement by context
            body.size = 15
        }
        .then(60, stand)
        .then(1) { (context) ->
            context["velocity"] = Point.randVector() * (Random.nextFloat() * 2f + 1f)
            val body: BodyElement by context
            body.size = 8
        }
        .then(2000, moveForward)
    val cbColorPattern = BehaviorPattern<UniversalPatternComponent>()
        .then(1) { it.context["color"] = Color.BLUE }
        .then(180, stand)
        .then(1) { it.context["color"] = Color.GREEN }
        .then(181, stand)
        .then(1) { it.context["color"] = Color.MAGENTA }
        .then(2000, stand)

    val cannonFirePattern = BehaviorPattern<UniversalPatternComponent>()
        .then(1) {
            val newBullets = List(50) { Bullet() }
                .onEachIndexed { i, b ->
                    b.addBehavior(cbMovePattern, i)
                    b.addBehavior(cbColorPattern)
                    b.context["velocity"] = Point(0, 0)
                }
            objectsToAdd.addAll(newBullets)
        }
        .then(29, stand)
        .repeat(7)
        .then(790, stand)
        .loop()

    val c2 = Cannon()
    c2.body.position = Point(100, 200)
    c2.behaviors.add(
        UniversalPatternComponent(
            BehaviorPattern<UniversalPatternComponent>()
                .then(moveTo(120) {
                    (Player.body.position - c2.body.position) * 0.3f + c2.body.position
                })
                .then(60, stand)
                .loop(),
            c2.context
        )
    )
    val c2bMovPattern = BehaviorPattern<UniversalPatternComponent>()
        .then(1) {
            val body: BodyElement by it.context
            body.position = c2.body.position
            body.size = 10
            it.context["velocity"] = Point(1, 0).rotate(PI / 3 * it.index + timer % 100).norm() * 1.4f
        }
        .then {
            moveForward(it)
            if (timer - it.createTime > 600)
                return@then
            val body: BodyElement by it.context
            val position = body.position
            val velocity: Point by it.context
            if (position.x > 1300 || position.x < 10) {
                velocity.x = -velocity.x
            }
            if (position.y > 800 || position.y < 10)
                velocity.y = -velocity.y
        }
    val c2bColPattern = BehaviorPattern<UniversalPatternComponent>()
        .then(1) { it.context["color"] = Color.WHITE }
        .then(f = stand)
    c2.behaviors.add(
        SimplePatternComponent(
            BehaviorPattern<SimplePatternComponent>()
                .then(1) {
                    objectsToAdd.addAll(
                        List(6) { Bullet() }
                            .onEachIndexed { i, b ->
                                b.addBehavior(c2bColPattern)
                                b.addBehavior(c2bMovPattern, i)
                                b.context.putAll(mapOf(
                                    "velocity" to Point(0, 0)
                                ))
                            }
                    )
                }
                .then(60, stand)
                .loop()
        )
    )
    c2.healthElement.health = 200

    val c3 = Cannon().apply cannon@ {
        healthElement.health = 200
        body.position = Point(600, 300)

        val bulletsPattern = BehaviorPattern<UniversalPatternComponent>()
            .then(1) {
                val cannon: Cannon by it.context
                val velocity: Point by it.context
                val body: BodyElement by it.context
                var color: Color by it.context

                val h = (timer.toFloat() / 5) % 360 / 360
                color = Color.getHSBColor(h, 1f, 1f)
                body.position = cannon.body.position
                velocity.set(Point(1, 0)
                    .rotate((PI * floor(it.index.toDouble() / 5)) + PI / 30 * (it.index % 5 + timer / 3)))
            }
            .then {
                val velocity: Point by it.context
                val body: BodyElement by it.context
                body.position += velocity
            }
        val ricochetPattern = BehaviorPattern<UniversalPatternComponent>()
            .then(1) {
                it.context["num"] = 1
            }
            .then {
                val body: BodyElement by it.context
                val position = body.position
                val velocity: Point by it.context
                var num: Int by it.context
                if (num == 0)
                    return@then
                if (position.x > 1480 || position.x < 10) {
                    velocity.x = -velocity.x
                    num = num - 1
                }
                if (position.y > 800 || position.y < 10) {
                    velocity.y = -velocity.y
                    num = num - 1
                }
            }
        val firePattern = BehaviorPattern<UniversalPatternComponent>()
            .then(1) {
                objectsToAdd.addAll(List(10) { Bullet() }
                    .onEachIndexed { i, b ->
                        b.context.putAll(
                            mapOf(
                                "cannon" to this@cannon,
                                "velocity" to Point(0, 0)
                            )
                        )
                        b.addBehavior(bulletsPattern, i)
                        b.addBehavior(ricochetPattern)
                    })
            }
            .then(5, stand)
            .loop()
        addBehavior(firePattern)
    }
    c.addBehavior(cannonFirePattern)
    cannons.add(c)
    cannons.add(c2)
    cannons.add(c3)
    gameObjects.addAll(cannons)
}