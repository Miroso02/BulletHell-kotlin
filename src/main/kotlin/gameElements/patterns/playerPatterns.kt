package gameElements.patterns

import Point
import cannons
import gameElements.Bullet
import gameElements.Player
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.SimplePatternComponent
import gameElements.components.patternComponents.UniversalPatternComponent
import gameElements.elements.BodyElement
import objectsToAdd
import objectsToRemove
import java.awt.Color
import java.awt.Graphics2D

val playerDisplayPattern1 = BehaviorPattern<UniversalPatternComponent>()
    .then {
        val graphics: Graphics2D? by it.context
        val body: BodyElement by it.context
        val position = body.position
        graphics?.let { g ->
            g.color = it.context["color"] as Color?
            g.fillOval(position.x.toInt(), position.y.toInt(), body.size, body.size)
        }
    }

val playerBulletsCleanupPattern = BehaviorPattern<UniversalPatternComponent>()
    .then {
        val body: BodyElement by it.context
        if (body.isOffScreen() ||
            cannons.any { c ->
                if (c != Player && !c.healthElement.isDead && c.body.collides(body)) {
                    c.healthElement.health--; true
                } else false
            }
        ) {
            objectsToRemove.add(it.context["obj"] as Bullet)
        }
    }

val playerFirePattern = BehaviorPattern<SimplePatternComponent>()
    .then(1) {
        val bullets = List(5) { Bullet(true) }
            .onEachIndexed { i, b ->
                b.body.position = Player.body.position + Point(0, 5)
                b.context["color"] = Color.GRAY
                val pattern = UniversalPatternComponent(
                    BehaviorPattern<UniversalPatternComponent>()
                        .then(3000, moveForward), b.context
                )
                b.context["velocity"] = Point((i - 1).toFloat() / 4, -8)
                b.behaviors.add(pattern)
            }
        objectsToAdd.addAll(bullets)
    }
    .then(4, stand)
    .loop()