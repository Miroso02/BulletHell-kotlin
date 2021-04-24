package gameElements.patterns

import gameElements.GameObject

abstract class BehaviorComponent(val obj: GameObject) {
    operator fun component1() = obj
    abstract fun update()
}