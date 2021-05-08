package gameElements

import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.*
import gameElements.components.patternComponents.DisplayComponent
import gameElements.elements.BodyElement
import gameElements.elements.ColorElement

abstract class GameObject {
    val behaviors = ArrayList<BehaviorComponent>()

    val body = BodyElement()
    val color = ColorElement()
    var displayComponent = DisplayComponent(BehaviorPattern(), this.body, this.color)

    open fun update() {
        for (beh in behaviors)
            beh.update()
    }
}