package gameElements

import Point
import player
import playerBullets
import java.awt.Color

class Player: Cannon() {
    init {
        position = Point(400, 500)
        size = 5
        color = Color.WHITE
        firePatterns.add {
            val bull = Array(3) { Bullet(player.position) }
            for (i in bull.indices) {
                bull[i].velocity = Point((i - 1).toFloat() / 2, -5f)
                bull[i].color = Color.WHITE
            }
            playerBullets.addAll(bull)
        }
    }

    override fun move() {

    }
}