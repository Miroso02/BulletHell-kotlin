package gameElements

import Point
import cannons
import gameElements.patterns.MovePattern
import java.awt.Color
import java.awt.Graphics2D

object Player : Cannon() {
    init {
        position = Point(400, 500)
        size = 1
        color = Color.WHITE
        val playerFirePattern = MovePattern()
                .then(1) { can ->
                    val bullets = List(5) { Bullet(it) }
                            .onEachIndexed { i, b ->
                                b.position = this.position
                                b.color = Color.GRAY
                                b.velocity = Point((i - 1).toFloat() / 4, -8)
                            }
                    this.bullets.addAll(bullets)
                }
                .then(4, stand)
        this.movePattern = playerFirePattern
    }

    override fun fire() {
    }

    override fun update(g: Graphics2D) {
        if (!isDead) {
            movePattern(this)
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