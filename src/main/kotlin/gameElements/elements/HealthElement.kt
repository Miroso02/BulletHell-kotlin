package gameElements.elements

class HealthElement(var health: Int = 1) {
    val isDead get() = health <= 0
}