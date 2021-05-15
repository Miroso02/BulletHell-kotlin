package gameElements

import gameElements.elements.HealthElement
import gameElements.patterns.cannonDisplayPattern1
import java.awt.Color

open class Cannon : GameObject() {
    val healthElement = HealthElement(100)

    init {
        context.putAll(mapOf(
            "size" to 40,
            "color" to Color.RED,
            "body" to this.body,
            "graphics" to null,
            "health" to healthElement
        ))
        body.size = 40
        addBehavior(cannonDisplayPattern1)
    }

    override fun update() {
        if (!healthElement.isDead) {
            super.update()
        }
    }
}