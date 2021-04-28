package gameElements

import gameElements.components.patternComponents.DisplayComponent
import gameElements.patterns.bulletDisplayPattern

class Bullet(index: Int = 0) : GameObject(index) {
    init {
        displayComponent = DisplayComponent(bulletDisplayPattern, this.body, this.color)
        behaviors.add(displayComponent)
    }
}