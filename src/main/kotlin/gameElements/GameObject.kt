package gameElements

import Point
import mainFrame
import timer
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
    var movePattern = MovePattern().then(3000, moveForward)
    var velocity = Point(0, 0)
    var accel: Point = Point(0, 0)
    val createTime = timer

    abstract fun display(g: Graphics2D)
    open fun move() {
        movePattern(this)
    }

    fun collides(obj: GameObject): Boolean {
        val (x, y) = obj.position - position
        val sumSize = size + obj.size
        return x * x + y * y < sumSize * sumSize / 4
    }
    fun isOffScreen(): Boolean {
        return x > mainFrame.size.width || x < 0 || y > mainFrame.size.height || y < 0
    }
}