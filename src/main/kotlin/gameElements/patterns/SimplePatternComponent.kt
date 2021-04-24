package gameElements.patterns

import gameElements.GameObject

class SimplePatternComponent(obj: GameObject, pattern: BehaviorPattern<SimplePatternComponent>): PatternComponent<SimplePatternComponent>(obj, pattern) {
    override fun update() {
        pattern(this)
    }
}