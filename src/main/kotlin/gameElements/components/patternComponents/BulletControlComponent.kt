package gameElements.components.patternComponents

import gameElements.Bullet
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.elements.BulletsElement

class BulletControlComponent(
    pattern: BehaviorPattern<BulletControlComponent>,
    private val bulletsElement: BulletsElement
) : PatternComponent<BulletControlComponent>(pattern) {
    val bullets: ArrayList<Bullet>
        get() = bulletsElement.bullets
}