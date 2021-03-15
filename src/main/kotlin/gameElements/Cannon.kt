package gameElements

import Point
import player
import java.awt.Color
import java.awt.Graphics2D

open class Cannon(position: Point = Point(0, 0)): GameObject(position) {
    var health = 100
    var isDead = false
    private val firePatterns = ArrayList<FirePattern>()
    val bullets = HashMap<FirePattern, ArrayList<Bullet>>()

    constructor(x: Int, y: Int): this(Point(x, y))

    init {
        size = 40
        color = Color.RED
    }

    fun fire() {
        firePatterns.forEach { bullets[it]?.addAll(it()) }
    }

    open fun update(g: Graphics2D) {
        if (health <= 0) isDead = true
        if (!isDead) {
            fire()
            display(g)
        }
        removeUselessBullets()
        bullets.values.forEach { arr ->
            arr.forEach { b ->
                with(b) {
                    display(g)
                    move()
                    if (collides(player))
                        player.isDead = true
                }
            }
        }
    }

    open fun removeUselessBullets() {
        bullets.forEach { (_, arr) -> arr.removeAll { it.isOffScreen() } }
    }

    fun addFirePatterns(vararg firePatterns: FirePattern) {
        firePatterns.forEach { this.firePatterns.add(it); bullets[it] = ArrayList() }
    }

    override fun display(g: Graphics2D) {
        g.color = color
        g.rect(position, size)
        g.drawString("$health", x, y)
    }

    override fun move() {}

    private fun Graphics2D.rect(position: Point, size: Int) {
        val (x, y) = position - Point(size / 2, size / 2)
        drawRect(x.toInt(), y.toInt(), size, size)
    }
}