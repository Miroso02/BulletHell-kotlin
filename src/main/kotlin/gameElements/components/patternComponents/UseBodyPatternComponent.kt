package gameElements.components.patternComponents

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.BodyComponent

abstract class UseBodyPatternComponent<T : UseBodyPatternComponent<T>>(
    pattern: BehaviorPattern<T>,
    private val bodyComponent: BodyComponent,
    index: Int = 0
) :
    PatternComponent<T>(pattern, index) {
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
    abstract override fun update()
}