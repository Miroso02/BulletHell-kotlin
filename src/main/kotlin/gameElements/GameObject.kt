package gameElements

import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.*
import gameElements.components.patternComponents.DisplayComponent

abstract class GameObject(val index: Int = 0) {
    val behaviors = ArrayList<BehaviorComponent>()

    val body = BodyComponent()
    val color = ColorComponent()
    var displayComponent = DisplayComponent(BehaviorPattern(), this.body, this.color)

    open fun update() {
        for (beh in behaviors)
            beh.update()
    }
}