package gameElements.components.patternComponents

import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.BehaviorComponent
import timer

abstract class PatternComponent<T : PatternComponent<T>>(
    val pattern: BehaviorPattern<T>,
    val index: Int = 0,
) :
    BehaviorComponent {
    var createTime = timer
    var curFunIndex = 0
    override fun update() {
        pattern(this as T)
    }
}