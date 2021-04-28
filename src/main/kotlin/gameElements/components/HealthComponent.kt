package gameElements.components

class HealthComponent(var health: Int = 1) : BehaviorComponent {
    val isDead get() = health <= 0
    override fun update() {}
}