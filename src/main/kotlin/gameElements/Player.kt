package gameElements

import Point
import java.awt.Color

class Player: Cannon() {
    init {
        position = Point(400, 500)
        size = 5
        color = Color.WHITE
        firePatterns.add(PlayerFirePattern(this))
    }

    override fun move() {

    }
}