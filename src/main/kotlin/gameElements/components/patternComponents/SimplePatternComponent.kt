package gameElements.components.patternComponents

import gameElements.behaviorPattern.BehaviorPattern

class SimplePatternComponent(pattern: BehaviorPattern<SimplePatternComponent>): PatternComponent<SimplePatternComponent>(pattern) {
    override fun update() {
        pattern(this)
    }
}