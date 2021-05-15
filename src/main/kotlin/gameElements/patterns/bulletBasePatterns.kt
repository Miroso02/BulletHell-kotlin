package gameElements.patterns

import gameElements.Bullet
import gameElements.Player
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.UniversalPatternComponent
import gameElements.elements.BodyElement
import objectsToRemove
import java.awt.Color
import java.awt.Graphics2D

val bulletsDisplayPattern1 = BehaviorPattern<UniversalPatternComponent>()
    .then {
        val graphics: Graphics2D? by it.context
        val body: BodyElement by it.context
        val position = body.position
        graphics?.apply {
            color = it.context["color"] as Color?
            fillOval(position.x.toInt(), position.y.toInt(), body.size, body.size)
        }
    }
val bulletCleanupPattern = BehaviorPattern<UniversalPatternComponent>()
    .then {
        val body: BodyElement by it.context
        if (body.collides(Player.body))
            Player.healthElement.health = 0
        if (body.isOffScreen())
            objectsToRemove.add(it.context["obj"] as Bullet)
    }