package gameElements.patterns

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.patternComponents.DisplayComponent

val cannonDisplayPattern = BehaviorPattern<DisplayComponent>()
    .then { context ->
        context.graphics?.let { g ->
            g.color = context.color
            val (x, y) = context.position - Point(context.size / 2, context.size / 2)
            g.drawRect(x.toInt(), y.toInt(), context.size, context.size)
        }
    }