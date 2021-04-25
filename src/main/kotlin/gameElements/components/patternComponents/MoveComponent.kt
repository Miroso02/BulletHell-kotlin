package gameElements.components.patternComponents

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.BodyComponent

class MoveComponent(
    movePattern: BehaviorPattern<MoveComponent>,
    private val bodyComponent: BodyComponent,
    index: Int = 0
) :
    PatternComponent<MoveComponent>(movePattern, index) {
    var position: Point
        get() = bodyComponent.position
        set(value) {
            bodyComponent.position = value
        }
    var size: Int
        get() = bodyComponent.size
        set(value) {
            bodyComponent.size = value
        }
    var velocity = Point(0, 0)
    var accel = Point(0, 0)
    override fun update() {
        pattern(this)
    }
}