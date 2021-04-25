package gameElements

import Point
import cannons
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.BulletControlComponent
import gameElements.components.patternComponents.DisplayComponent
import gameElements.components.patternComponents.MoveComponent
import java.awt.Color
import java.awt.Graphics2D

object Player : Cannon() {
    private val playerDisplayPattern = BehaviorPattern<DisplayComponent>()
        .then { context ->
            context.graphics?.let { g ->
                g.color = context.color
                g.fillOval(context.position.x.toInt(), context.position.y.toInt(), context.size, context.size)
            }
        }

    init {
        displayComponent = DisplayComponent(playerDisplayPattern, this.body, this.color)
        body.position = Point(400, 500)
        body.size = 1
        displayComponent.color = Color.WHITE
        behaviors.add(displayComponent)

        val playerFirePattern = BehaviorPattern<BulletControlComponent>()
                .then(1) { context ->
                    val bullets = List(5) { Bullet(it) }
                            .onEachIndexed { i, b ->
                                b.body.position = this.body.position
                                b.displayComponent.color = Color.GRAY
                                val pattern = MoveComponent(
                                    BehaviorPattern<MoveComponent>()
                                    .then(3000, moveForward), b.body)
                                pattern.velocity = Point((i - 1).toFloat() / 4, -8)
                                b.behaviors.add(pattern)
                            }
                    context.bullets.addAll(bullets)
                }
                .then(4, stand)
        this.behaviors.add(BulletControlComponent(playerFirePattern, this.bullets))
    }

    override fun update(g: Graphics2D) {
        if (!isDead) {
            displayComponent.graphics = g
            update()
        }
        removeUselessBullets()
        bullets.forEach { b ->
            b.displayComponent.graphics = g
            b.update()
        }
    }

    override fun removeUselessBullets() {
        bullets.removeAll {
            it.body.isOffScreen() ||
                    cannons.any { c ->
                        if (c != this && !c.isDead && c.body.collides(it.body)) {
                            c.health--; true
                        } else false
                    }
        }
    }
}