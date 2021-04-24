package gameElements

import Point
import java.awt.Graphics2D

class Bullet(position: Point = Point(0, 0), index: Int = 0) : GameObject(position, index) {
    override fun display(g: Graphics2D) {
        g.color = color
        g.ellipse(position, size)
    }

    private fun Graphics2D.ellipse(position: Point, size: Int) {
        fillOval(position.x.toInt(), position.y.toInt(), size, size)
    }
}