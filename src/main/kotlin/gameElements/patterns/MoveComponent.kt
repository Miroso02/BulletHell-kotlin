package gameElements.patterns

import Point
import gameElements.GameObject

class MoveComponent(obj: GameObject, movePattern: BehaviorPattern<MoveComponent>) : PatternComponent<MoveComponent>(obj, movePattern) {
    var velocity = Point(0, 0)
    var accel = Point(0, 0)
    override fun update() {
        pattern(this)
    }
}