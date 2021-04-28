package gameElements.patterns

import Point
import cannons
import gameElements.Bullet
import gameElements.Player
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.BulletControlComponent
import gameElements.components.patternComponents.DisplayComponent
import gameElements.components.patternComponents.MoveComponent
import java.awt.Color

val playerDisplayPattern = BehaviorPattern<DisplayComponent>()
    .then { context ->
        context.graphics?.let { g ->
            g.color = context.color
            g.fillOval(context.position.x.toInt(), context.position.y.toInt(), context.size, context.size)
        }
    }

val playerBulletControlPattern = BehaviorPattern<BulletControlComponent>()
    .then { context ->
        context.bullets.removeAll {
            it.body.isOffScreen() ||
                    cannons.any { c ->
                        if (c != Player && !c.healthComponent.isDead && c.body.collides(it.body)) {
                            c.healthComponent.health--; true
                        } else false
                    }
        }
        context.bullets.forEach { b ->
            b.update()
        }
    }

val playerFirePattern = BehaviorPattern<BulletControlComponent>()
    .then(1) { context ->
        val bullets = List(5) { Bullet(it) }
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
        context.bullets.addAll(bullets)
    }
    .then(4, stand)
    .loop()