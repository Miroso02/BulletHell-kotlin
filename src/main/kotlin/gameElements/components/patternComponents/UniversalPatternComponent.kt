package gameElements.components.patternComponents

import gameElements.behaviorPattern.BehaviorPattern

class UniversalPatternComponent(
    pattern: BehaviorPattern<UniversalPatternComponent>,
    val context: HashMap<String, Any?>,
    index: Int = 0
) : PatternComponent<UniversalPatternComponent>(pattern, index) {
    operator fun component1() = context
    operator fun component2() = index
}