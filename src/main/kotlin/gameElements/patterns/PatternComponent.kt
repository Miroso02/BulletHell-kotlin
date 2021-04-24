package gameElements.patterns

import gameElements.GameObject

abstract class PatternComponent<T: PatternComponent<T>>(obj: GameObject, val pattern: BehaviorPattern<T>): BehaviorComponent(obj) {
    var curFunIndex = 0
    abstract override fun update()
}