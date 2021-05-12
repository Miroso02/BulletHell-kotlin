package gameElements.components.patternComponents

import gameElements.behaviorPattern.BehaviorPattern

class UniversalPatternComponent(
    pattern: BehaviorPattern<UniversalPatternComponent>,
    index: Int = 0
) : PatternComponent<UniversalPatternComponent>(pattern, index) {
    val context = HashMap<String, Any>()
}