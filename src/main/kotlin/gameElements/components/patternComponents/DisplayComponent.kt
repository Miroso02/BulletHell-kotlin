package gameElements.components.patternComponents

import gameElements.behaviorPattern.BehaviorPattern
import gameElements.elements.BodyElement
import gameElements.elements.ColorElement
import java.awt.Color
import java.awt.Graphics2D

class DisplayComponent(
    displayPattern: BehaviorPattern<DisplayComponent>,
    bodyElement: BodyElement,
    private val colorElement: ColorElement,
    index: Int = 0
) : UseBodyPatternComponent<DisplayComponent>(displayPattern, bodyElement, index) {
    var color: Color
        get() = colorElement.color
        set(value) {
            colorElement.color = value
        }
    var graphics: Graphics2D? = null
}