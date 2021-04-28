package gameElements.components.patternComponents

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.BodyComponent
import gameElements.components.ColorComponent
import java.awt.Color
import java.awt.Graphics2D

class DisplayComponent(
    pattern: BehaviorPattern<DisplayComponent>,
    bodyComponent: BodyComponent,
    private val colorComponent: ColorComponent,
    index: Int = 0
) : UseBodyPatternComponent<DisplayComponent>(pattern, bodyComponent, index) {
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