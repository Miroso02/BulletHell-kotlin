package gameElements

import Point
import java.awt.Color
import java.awt.Graphics2D

open class Cannon(position: Point = Point(0, 0)): GameObject(position) {
    var health = 100
    val firePatterns = ArrayList<() -> Unit>()

    constructor(x: Int, y: Int): this(Point(x, y))

    init {
        size = 40
        color = Color.RED
    }

    fun fire() {
        firePatterns.forEach { it() }
    }

    override fun display(g: Graphics2D) {
        g.color = color
        g.rect(position, size)
    }

    override fun move() {}

    private fun Graphics2D.rect(position: Point, size: Int) {
        val (x, y) = position - Point(size / 2, size / 2)
        drawRect(x.toInt(), y.toInt(), size, size)
    }
}