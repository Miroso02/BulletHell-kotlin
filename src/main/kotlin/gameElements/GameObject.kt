package gameElements

import Point
import MainFrame
import timer
import java.awt.Color
import java.awt.Graphics2D

abstract class GameObject(var position: Point) {
    var size = 5
    var color: Color = Color.WHITE
    var x: Float
        get() = position.x
        set(value) {
            position.x = value
        }
    var y: Float
        get() = position.y
        set(value) {
            position.y = value
        }
    var movePattern = MovePattern().then(3000, moveForward)
    var velocity = Point(0, 0)
    var accel = Point(0, 0)
    val createTime = timer
    var curMovFuncInd = 0

    abstract fun display(g: Graphics2D)

    open fun move() {
        movePattern(this)
    }

    fun collides(obj: GameObject): Boolean = (obj.position - position).mag() < (size + obj.size) / 2
    fun isOffScreen(): Boolean = x > MainFrame.size.width || x < 0 || y > MainFrame.size.height || y < 0
}