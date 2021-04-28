package gameElements.components.patternComponents

import gameElements.Bullet
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.BulletsComponent

class BulletControlComponent(
    pattern: BehaviorPattern<BulletControlComponent>,
    private val bulletsComponent: BulletsComponent
) : PatternComponent<BulletControlComponent>(pattern) {
    val bullets: ArrayList<Bullet>
        get() = bulletsComponent.bullets
    override fun update() {
        pattern(this)
    }
}