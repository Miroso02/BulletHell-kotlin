package gameElements.elements

import Point

class BodyElement {
    var position = Point(0, 0)
    var size = 5

    fun collides(body: BodyElement): Boolean = (position - body.position).mag() < (size + body.size) / 2
    fun isOffScreen(): Boolean =
        position.x > MainFrame.size.width
                || position.x < 0
                || position.y > MainFrame.size.height
                || position.y < 0
}