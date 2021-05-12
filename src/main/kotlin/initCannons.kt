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

    val cannonFirePattern = BehaviorPattern<BulletControlComponent>()
        .then(1) {
            val newBullets = List(50) { Bullet() }
                .onEachIndexed { i, b ->
                    b.behaviors.add(MoveComponent(cbMovePattern, b.body, i))
                    b.behaviors.add(DisplayComponent(cbColorPattern, b.body, b.color, i))
                }
            bullets.addAll(newBullets)
        }
        .then(29, stand)
        .repeat(7)
        .then(790, stand)
        .loop()

    val c2 = Cannon()
    c2.body.position = Point(100, 200)
    c2.behaviors.add(
        MoveComponent(
            BehaviorPattern<MoveComponent>()
                .then(moveTo(120) {
                    (Player.body.position - c2.body.position) * 0.3f + c2.body.position
                })
                .then(60, stand)
                .loop(),
            c2.body
        )
    )
    val c2bMovPattern = BehaviorPattern<MoveComponent>()
        .then(1) {
            it.position = c2.body.position
            it.size = 10
            it.velocity = Point(1, 0).rotate(PI / 3 * it.index + timer % 100).norm() * 1.4f
        }
        .then {
            moveForward(it)
            if (timer - it.createTime > 600)
                return@then
            if (it.position.x > 1300 || it.position.x < 10) {
                it.velocity.x = -it.velocity.x
            }
            if (it.position.y > 800 || it.position.y < 10)
                it.velocity.y = -it.velocity.y
        }
    val c2bColPattern = BehaviorPattern<DisplayComponent>()
        .then(1) { it.color = Color.MAGENTA }
        .then(f = stand)
    c2.behaviors.add(
        SimplePatternComponent(
            BehaviorPattern<SimplePatternComponent>()
                .then(1) {
                    bullets.addAll(
                        List(6) { Bullet() }
                            .onEachIndexed { i, b ->
                                b.addMoveComponent(c2bMovPattern, i)
                                b.addDisplayComponent(c2bColPattern)
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
                val bullet = it.context["object"] as Bullet
                val h = (timer.toFloat() / 5) % 360 / 360
                bullet.color.color = Color.getHSBColor(h, 1f, 1f)
                bullet.body.position = (it.context["cannon"] as Cannon).body.position
                (it.context["velocity"] as Point).set(Point(1, 0)
                    .rotate((PI * floor(it.index.toDouble() / 5)) + PI / 30 * (it.index % 5 + timer / 3)))
            }
            .then {
                (it.context["object"] as Bullet).body.position += it.context["velocity"] as Point
            }
        val ricochetPattern = BehaviorPattern<UniversalPatternComponent>()
            .then(1) {
                it.context["num"] = 1
            }
            .then {
                val position = (it.context["body"] as BodyElement).position
                val velocity = it.context["velocity"] as Point
                val n = it.context["num"] as Int
                if (n == 0)
                    return@then
                if (position.x > 1480 || position.x < 10) {
                    velocity.x = -velocity.x
                    it.context["num"] = n - 1
                }
                if (position.y > 800 || position.y < 10) {
                    velocity.y = -velocity.y
                    it.context["num"] = n - 1
                }
            }
        val firePattern = BehaviorPattern<UniversalPatternComponent>()
            .then(1) {
                bullets.addAll(List(10) { Bullet() }
                    .onEachIndexed { i, b ->
                        val comp = UniversalPatternComponent(bulletsPattern, i)
                            .apply {
                                context["object"] = b
                                context["cannon"] = this@cannon
                                context["velocity"] = Point(0, 0)
                            }
                        val comp2 = UniversalPatternComponent(ricochetPattern)
                            .apply {
                                context["body"] = b.body
                                context["velocity"] = comp.context["velocity"] as Point
                            }
                        b.behaviors.add(comp)
                        b.behaviors.add(comp2)
                    })
            }
            .then(5, stand)
            .loop()
        behaviors.add(UniversalPatternComponent(firePattern))
    }
    c.behaviors.add(BulletControlComponent(cannonFirePattern, c.bulletsElement))
    //cannons.add(c)
    //cannons.add(c2)
    cannons.add(c3)
}