package gameElements

import Point
import java.awt.Color
import java.awt.Graphics2D

open class Cannon(position: Point = Point(0, 0)) : GameObject(position) {
    var health = 100
    var isDead = false

    val bullets = ArrayList<Bullet>()

    constructor(x: Int, y: Int) : this(Point(x, y))

    init {
        size = 40
        color = Color.RED
    }

    open fun fire() {

    }

    open fun update(g: Graphics2D) {
        if (health <= 0) this.isDead = true
        if (!isDead) {
            fire()
            move()
            display(g)
        }
        removeUselessBullets()
        bullets.forEach { b ->
            with(b) {
                display(g)
                move()
//                if (collides(Player))
//                    Player.isDead = true
            }
        }
    }

    open fun removeUselessBullets() {
        bullets.removeAll { it.isOffScreen() }
    }

    override fun display(g: Graphics2D) {
        g.color = color
        g.rect(position, size)
        g.drawString("$health", x, y)
    }

    private fun Graphics2D.rect(position: Point, size: Int) {
        val (x, y) = position - Point(size / 2, size / 2)
        drawRect(x.toInt(), y.toInt(), size, size)
    }
}