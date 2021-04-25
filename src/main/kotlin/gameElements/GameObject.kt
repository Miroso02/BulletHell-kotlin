package gameElements

import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.*
import gameElements.components.patternComponents.DisplayComponent
import timer
import java.awt.Graphics2D

abstract class GameObject(val index: Int = 0) {
    var createTime = timer
    val body = BodyComponent()
    val color = ColorComponent()
    var displayComponent = DisplayComponent(BehaviorPattern(), this.body, this.color)
    val behaviors = ArrayList<BehaviorComponent>().apply { add(body) }

    open fun update() {
        for (beh in behaviors)
            beh.update()
    }
}