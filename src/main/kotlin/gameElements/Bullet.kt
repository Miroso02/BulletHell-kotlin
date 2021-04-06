package gameElements

import Point
import java.awt.Graphics2D

class Bullet(private val index: Int, position: Point = Point(0, 0)) : GameObject(position) {
    override fun move() {
        movePattern(this, index)
    }

    override fun display(g: Graphics2D) {
        g.color = color
        g.ellipse(position, size)
    }

    private fun Graphics2D.ellipse(position: Point, size: Int) {
        fillOval(position.x.toInt(), position.y.toInt(), size, size)
    }
}