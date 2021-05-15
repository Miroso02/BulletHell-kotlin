package gameElements

import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.BehaviorComponent
import gameElements.components.patternComponents.UniversalPatternComponent
import gameElements.elements.BodyElement

abstract class GameObject {
    val behaviors = ArrayList<BehaviorComponent>()

    val context = HashMap<String, Any?>()
    val body = BodyElement()

    open fun update() {
        for (beh in behaviors)
            beh.update()
    }

    fun addBehavior(behaviorPattern: BehaviorPattern<UniversalPatternComponent>, index: Int = 0) {
        behaviors.add(UniversalPatternComponent(behaviorPattern, this.context, index))
    }
}