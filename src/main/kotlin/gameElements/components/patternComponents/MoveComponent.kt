package gameElements.components.patternComponents

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.BodyComponent

class MoveComponent(
    movePattern: BehaviorPattern<MoveComponent>,
    bodyComponent: BodyComponent,
    index: Int = 0
) :
    UseBodyPatternComponent<MoveComponent>(movePattern, bodyComponent, index) {
    var velocity = Point(0, 0)
    var accel = Point(0, 0)
    override fun update() {
        pattern(this)
    }
}