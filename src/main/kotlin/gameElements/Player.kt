package gameElements

import Point
import gameElements.components.patternComponents.DisplayComponent
import gameElements.components.patternComponents.SimplePatternComponent
import gameElements.patterns.playerDisplayPattern
import gameElements.patterns.playerFirePattern
import java.awt.Color

object Player : Cannon() {
    init {
        displayComponent = DisplayComponent(playerDisplayPattern, this.body, this.color)
        body.position = Point(400, 500)
        body.size = 1
        displayComponent.color = Color.WHITE
        behaviors.add(displayComponent)
        behaviors.add(SimplePatternComponent(playerFirePattern))
    }
}