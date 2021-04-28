package gameElements

import gameElements.components.BulletsComponent
import gameElements.components.HealthComponent
import gameElements.components.patternComponents.BulletControlComponent
import gameElements.components.patternComponents.DisplayComponent
import gameElements.patterns.cannonBulletControlPattern
import gameElements.patterns.cannonDisplayPattern
import java.awt.Color
import java.awt.Graphics2D

open class Cannon : GameObject() {
    val healthComponent = HealthComponent(100)
    val bulletsComponent = BulletsComponent()

    private val cannonBulletControlComponent = BulletControlComponent(cannonBulletControlPattern, this.bulletsComponent)

    init {
        displayComponent = DisplayComponent(cannonDisplayPattern, this.body, this.color)
        body.size = 40
        displayComponent.color = Color.RED
        behaviors.add(displayComponent)
    }

    // TODO: Fix signature
    open fun update(g: Graphics2D) {
        for (b in bulletsComponent.bullets)
            b.displayComponent.graphics = g
        cannonBulletControlComponent.update()
        if (!healthComponent.isDead) {
            displayComponent.graphics = g
            update()
        }
    }
}