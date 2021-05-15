package gameElements

import gameElements.patterns.bulletCleanupPattern
import gameElements.patterns.bulletsDisplayPattern1
import gameElements.patterns.playerBulletsCleanupPattern
import java.awt.Color

class Bullet(playerBullet: Boolean = false) : GameObject() {
    init {
        addBehavior(bulletsDisplayPattern1)
        addBehavior(if (playerBullet) playerBulletsCleanupPattern else bulletCleanupPattern)
        context.putAll(
            mapOf(
                "size" to 5,
                "color" to Color.WHITE,
                "body" to this.body,
                "graphics" to null,
                "obj" to this
            )
        )
    }
}