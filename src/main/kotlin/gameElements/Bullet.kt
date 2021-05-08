package gameElements

import gameElements.components.patternComponents.DisplayComponent
import gameElements.patterns.bulletDisplayPattern

class Bullet : GameObject() {
    init {
        displayComponent = DisplayComponent(bulletDisplayPattern, this.body, this.color)
        behaviors.add(displayComponent)
    }
}