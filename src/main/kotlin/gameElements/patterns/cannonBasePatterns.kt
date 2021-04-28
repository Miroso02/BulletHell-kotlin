package gameElements.patterns

import gameElements.Player
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.BulletControlComponent

val cannonBulletControlPattern = BehaviorPattern<BulletControlComponent>()
    .then { context ->
        context.bullets.removeAll { it.body.isOffScreen() }
        context.bullets.forEach { b ->
            with(b) {
                update()
                if (body.collides(Player.body))
                    Player.healthComponent.health = 0
            }
        }
    }