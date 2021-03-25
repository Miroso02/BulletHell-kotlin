package gameElements

import Point
import java.awt.Color
import java.awt.Graphics2D

class Bullet(position: Point = Point(0, 0)): GameObject(position) {
    init {
        color = Color.RED
    }

    // TODO: Implement GameObject's move() (or remove it)
    // TODO: Save index in bullet
    fun move(i: Int) {
        movePattern(this, i)
    }

    override fun display(g: Graphics2D) {
        g.color = color
        g.ellipse(position, size)
    }
    private fun Graphics2D.ellipse(position: Point, size: Int) {
        fillOval(position.x.toInt(), position.y.toInt(), size, size)
    }
}