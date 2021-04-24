package gameElements

import Point
import MainFrame
import gameElements.patterns.BehaviorComponent
import gameElements.patterns.BehaviorPattern
import gameElements.patterns.MoveComponent
import timer
import java.awt.Color
import java.awt.Graphics2D

abstract class GameObject(var position: Point, val index: Int = 0) {
    var size = 5
    var color: Color = Color.WHITE
    val behaviors = ArrayList<BehaviorComponent>()
    var createTime = timer
    var curMovFuncInd = 0

    abstract fun display(g: Graphics2D)

    open fun move() {
        for (beh in behaviors)
            beh.update()
    }

    fun collides(obj: GameObject): Boolean = (obj.position - position).mag() < (size + obj.size) / 2
    fun isOffScreen(): Boolean =
        position.x > MainFrame.size.width || position.x < 0 || position.y > MainFrame.size.height || position.y < 0
}