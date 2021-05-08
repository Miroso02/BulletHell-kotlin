package gameElements.components.patternComponents

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.elements.BodyElement

class MoveComponent(
    movePattern: BehaviorPattern<MoveComponent>,
    bodyElement: BodyElement,
    index: Int = 0
) :
    UseBodyPatternComponent<MoveComponent>(movePattern, bodyElement, index) {
    var velocity = Point(0, 0)
    var accel = Point(0, 0)
}