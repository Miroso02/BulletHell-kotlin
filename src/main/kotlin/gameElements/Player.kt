package gameElements

import Point
import gameElements.components.patternComponents.BulletControlComponent
import gameElements.components.patternComponents.DisplayComponent
import gameElements.patterns.playerBulletControlPattern
import gameElements.patterns.playerDisplayPattern
import gameElements.patterns.playerFirePattern
import java.awt.Color
import java.awt.Graphics2D

object Player : Cannon() {
    private val playerBulletControlComponent =
        BulletControlComponent(playerBulletControlPattern, Player.bulletsComponent)

    init {
        displayComponent = DisplayComponent(playerDisplayPattern, this.body, this.color)
        body.position = Point(400, 500)
        body.size = 1
        displayComponent.color = Color.WHITE
        behaviors.add(displayComponent)

        this.behaviors.add(BulletControlComponent(playerFirePattern, this.bulletsComponent))
    }

    override fun update(g: Graphics2D) {
        for (b in bulletsComponent.bullets)
            b.displayComponent.graphics = g
        playerBulletControlComponent.update()
        if (!healthComponent.isDead) {
            displayComponent.graphics = g
            update()
        }
    }
}