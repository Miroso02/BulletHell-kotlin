package gameElements

import Point
import mainFrame
import java.awt.Color
import java.awt.Graphics2D

abstract class GameObject(var position: Point) {
    var size = 5
    var color: Color = Color.WHITE
    var x: Float
        get() = position.x
        set(value) { position.x = value }
    var y: Float
        get() = position.y
        set(value) { position.y = value }
    constructor(x: Int, y: Int): this(Point(x, y))

    abstract fun display(g: Graphics2D)

    fun collides(obj: GameObject): Boolean {
        val (x, y) = obj.position - position
        val sumSize = size + obj.size
        return x * x + y * y < sumSize * sumSize
    }
    fun isOffScreen(): Boolean {
        return x > mainFrame.size.width || x < 0 || y > mainFrame.size.height || y < 0
    }
}