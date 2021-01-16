package gameElements

import Point
import java.awt.Color
import java.awt.Graphics2D

class Bullet(position: Point = Point(0, 0)): GameObject(position) {
    var velocity = Point(0, 0)
    constructor(x: Int, y: Int): this(Point(x, y))

    init {
        color = Color.RED
    }

    override fun display(g: Graphics2D) {
        g.color = color
        g.ellipse(position, size)
    }
    override fun move() { position += velocity }
    private fun Graphics2D.ellipse(position: Point, size: Int) {
        fillOval(position.x.toInt(), position.y.toInt(), size, size)
    }
}