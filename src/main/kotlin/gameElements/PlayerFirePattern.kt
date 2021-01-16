package gameElements

import Point
import player
import playerBullets
import timer
import java.awt.Color

class PlayerFirePattern(cannon: Cannon): FirePattern(cannon) {
    private val directions = listOf(Point(-1, -10), Point(0, -10), Point(1, -10))
    override fun fire() {
        if (timer % 5 != 0) return
        val bul = Array(3) { Bullet(cannon.position) }
        for (i in bul.indices) {
            bul[i].color = Color.WHITE
            bul[i].velocity = directions[i]
            bul[i].size = 5
        }
        playerBullets.addAll(bul)
    }
}