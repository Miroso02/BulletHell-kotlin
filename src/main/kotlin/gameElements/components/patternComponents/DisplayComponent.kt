package gameElements.components.patternComponents

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.BodyComponent
import gameElements.components.ColorComponent
import java.awt.Color
import java.awt.Graphics2D

class DisplayComponent(
    pattern: BehaviorPattern<DisplayComponent>,
    private val bodyComponent: BodyComponent,
    private val colorComponent: ColorComponent,
    index: Int = 0
) : PatternComponent<DisplayComponent>(pattern, index) {
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
    var color: Color
        get() = colorComponent.color
        set(value) {
            colorComponent.color = value
        }
    var graphics: Graphics2D? = null

    override fun update() {
        pattern(this)
    }
}