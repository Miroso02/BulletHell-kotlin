package gameElements

import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.DisplayComponent
import gameElements.components.patternComponents.SimplePatternComponent
import java.awt.Color
import java.awt.Graphics2D

open class Cannon : GameObject() {
    var health = 100
    var isDead = false
    val bullets = ArrayList<Bullet>()

    protected val cannonBulletControlComponent = SimplePatternComponent(BehaviorPattern<SimplePatternComponent>()
        .then {
            bullets.removeAll { it.body.isOffScreen() }
            bullets.forEach { b ->
                with(b) {
                    update()
                    if (body.collides(Player.body))
                        Player.isDead = true
                }
            }
        })

    init {
        displayComponent = DisplayComponent(cannonDisplayPattern, this.body, this.color)
        body.size = 40
        displayComponent.color = Color.RED
        behaviors.add(displayComponent)
    }

    open fun update(g: Graphics2D) {
        for (b in bullets)
            b.displayComponent.graphics = g
        cannonBulletControlComponent.update()
        if (health <= 0) this.isDead = true
        if (!isDead) {
            displayComponent.graphics = g
            update()
        }
    }

    open fun removeUselessBullets() {
        bullets.removeAll { it.body.isOffScreen() }
    }
}