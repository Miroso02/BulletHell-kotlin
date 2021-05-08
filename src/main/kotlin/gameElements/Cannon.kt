package gameElements

import gameElements.components.patternComponents.DisplayComponent
import gameElements.elements.BulletsElement
import gameElements.patterns.HealthElement
import gameElements.patterns.cannonDisplayPattern
import java.awt.Color

open class Cannon : GameObject() {
    val healthElement = HealthElement(100)
    val bulletsElement = BulletsElement()

    init {
        displayComponent = DisplayComponent(cannonDisplayPattern, this.body, this.color)
        body.size = 40
        displayComponent.color = Color.RED
        behaviors.add(displayComponent)
    }

    override fun update() {
        if (!healthElement.isDead) {
            super.update()
        }
    }
}