package gameElements.components.patternComponents

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.elements.BodyElement

abstract class UseBodyPatternComponent<T : UseBodyPatternComponent<T>>(
    pattern: BehaviorPattern<T>,
    val bodyElement: BodyElement,
    index: Int = 0
) :
    PatternComponent<T>(pattern, index) {
    var position: Point
        get() = bodyElement.position
        set(value) {
            bodyElement.position = value
        }
    var size: Int
        get() = bodyElement.size
        set(value) {
            bodyElement.size = value
        }
    fun collides(body: BodyElement) = bodyElement.collides(body)
    fun isOffScreen() = bodyElement.isOffScreen()
}