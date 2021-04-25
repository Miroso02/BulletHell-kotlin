package gameElements.components.patternComponents

import gameElements.Bullet
import gameElements.behaviorPattern.BehaviorPattern

class BulletControlComponent(
    pattern: BehaviorPattern<BulletControlComponent>,
    val bullets: ArrayList<Bullet>
) : PatternComponent<BulletControlComponent>(pattern) {
    override fun update() {
        pattern(this)
    }
}