package gameElements.patterns

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.UniversalPatternComponent
import gameElements.elements.BodyElement
import gameElements.elements.HealthElement
import java.awt.Color
import java.awt.Graphics2D

val cannonDisplayPattern1 = BehaviorPattern<UniversalPatternComponent>()
    .then {
        val graphics: Graphics2D? by it.context
        val body: BodyElement by it.context
        val health: HealthElement by it.context
        graphics?.let { g ->
            g.color = it.context["color"] as Color?
            val (x, y) = body.position - Point(body.size / 2, body.size / 2)
            g.drawRect(x.toInt(), y.toInt(), body.size, body.size)
            g.drawString(health.health.toString(), x + body.size / 4, y + body.size / 2)
        }
    }