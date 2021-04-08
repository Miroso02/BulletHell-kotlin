package gameElements

import Point
import cannons
import gameElements.patterns.FirePattern
import java.awt.Color
import java.awt.Graphics2D

object Player : Cannon() {
    init {
        position = Point(400, 500)
        size = 1
        color = Color.WHITE
        addFirePatterns(FirePattern(5)
                .setDelay(5)
                .apply {
                    it.position = this.position
                    it.color = Color.GRAY
                }
                .applyIndexed { i, b ->
                    b.velocity = Point((i - 1).toFloat() / 4, -8)
                })
    }

    override fun update(g: Graphics2D) {
        if (!isDead) {
            fire()
            display(g)
        }
        removeUselessBullets()
        bullets.values.forEach { arr ->
            arr.forEach { b ->
                b.display(g)
                b.move()
            }
        }
    }

    override fun removeUselessBullets() {
        bullets.forEach { (_, arr) ->
            arr.removeAll {
                it.isOffScreen() ||
                        cannons.any { c ->
                            if (c != this && !c.isDead && c.collides(it)) {
                                c.health--; true
                            } else false
                        }
            }
        }
    }

    override fun display(g: Graphics2D) {
        g.color = color
        g.fillOval(position.x.toInt(), position.y.toInt(), size, size)
    }
}