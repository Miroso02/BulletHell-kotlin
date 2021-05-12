package gameElements

import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.*
import gameElements.components.patternComponents.DisplayComponent
import gameElements.components.patternComponents.MoveComponent
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
    fun addMoveComponent(pattern: BehaviorPattern<MoveComponent>, index: Int = 0) {
        behaviors.add(MoveComponent(pattern, this.body, index))
    }
    fun addDisplayComponent(pattern: BehaviorPattern<DisplayComponent>, index: Int = 0) {
        behaviors.add(DisplayComponent(pattern, this.body, this.color, index))
    }
}