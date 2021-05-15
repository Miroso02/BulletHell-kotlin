package gameElements

import Point
import gameElements.components.patternComponents.SimplePatternComponent
import gameElements.patterns.playerDisplayPattern1
import gameElements.patterns.playerFirePattern
import java.awt.Color

object Player : Cannon() {
    init {
        body.position = Point(400, 500)
        body.size = 1
        context.putAll(mapOf(
            "size" to 1,
            "color" to Color.WHITE,
            "body" to this.body,
            "graphics" to null
        ))
        addBehavior(playerDisplayPattern1)
        behaviors.add(SimplePatternComponent(playerFirePattern))
    }
}