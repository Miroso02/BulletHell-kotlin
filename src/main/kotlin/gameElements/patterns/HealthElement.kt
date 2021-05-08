package gameElements.patterns

class HealthElement(var health: Int = 1) {
    val isDead get() = health <= 0
}