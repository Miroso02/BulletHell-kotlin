package gameElements.patterns

import Point
import gameElements.Bullet
import gameElements.Player
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.BulletControlComponent
import gameElements.components.patternComponents.DisplayComponent
import gameElements.components.patternComponents.MoveComponent
import gameElements.components.patternComponents.SimplePatternComponent
import playerBullets
import java.awt.Color

val playerDisplayPattern = BehaviorPattern<DisplayComponent>()
    .then { context ->
        context.graphics?.let { g ->
            g.color = context.color
            g.fillOval(context.position.x.toInt(), context.position.y.toInt(), context.size, context.size)
        }
    }

val playerFirePattern = BehaviorPattern<SimplePatternComponent>()
    .then(1) {
        val bullets = List(5) { Bullet() }
            .onEachIndexed { i, b ->
                b.body.position = Player.body.position
                b.displayComponent.color = Color.GRAY
                val pattern = MoveComponent(
                    BehaviorPattern<MoveComponent>()
                        .then(3000, moveForward), b.body
                )
                pattern.velocity = Point((i - 1).toFloat() / 4, -8)
                b.behaviors.add(pattern)
            }
        playerBullets.addAll(bullets)
    }
    .then(4, stand)
    .loop()