package gameElements

import Point
import cannons
import gameElements.patterns.BehaviorPattern
import gameElements.patterns.MoveComponent
import gameElements.patterns.SimplePatternComponent
import java.awt.Color
import java.awt.Graphics2D

object Player : Cannon() {
    init {
        position = Point(400, 500)
        size = 1
        color = Color.WHITE
        val playerFirePattern = BehaviorPattern<SimplePatternComponent>()
                .then(1) { can ->
                    val bullets = List(5) { Bullet(index = it) }
                            .onEachIndexed { i, b ->
                                b.position = this.position
                                b.color = Color.GRAY
                                val pattern = MoveComponent(b, BehaviorPattern<MoveComponent>().then(3000, moveForward))
                                pattern.velocity = Point((i - 1).toFloat() / 4, -8)
                                b.behaviors.add(pattern)
                            }
                    this.bullets.addAll(bullets)
                }
                .then(4, stand)
        this.behaviors.add(SimplePatternComponent(this, playerFirePattern))
    }

    override fun update(g: Graphics2D) {
        if (!isDead) {
            move()
            display(g)
        }
        removeUselessBullets()
        bullets.forEach { b ->
            b.display(g)
            b.move()
        }
    }

    override fun removeUselessBullets() {
        bullets.removeAll {
            it.isOffScreen() ||
                    cannons.any { c ->
                        if (c != this && !c.isDead && c.collides(it)) {
                            c.health--; true
                        } else false
                    }
        }
    }

    override fun display(g: Graphics2D) {
        g.color = color
        g.fillOval(position.x.toInt(), position.y.toInt(), size, size)
    }
}