package gameElements.components

import Point

class BodyComponent : BehaviorComponent {
    var position = Point(0, 0)
    var size = 5
    override fun update() {}

    fun collides(body: BodyComponent): Boolean = (position - body.position).mag() < (size + body.size) / 2
    fun isOffScreen(): Boolean =
        position.x > MainFrame.size.width
                || position.x < 0
                || position.y > MainFrame.size.height
                || position.y < 0
}