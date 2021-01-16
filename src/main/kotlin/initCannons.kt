import gameElements.Bullet
import gameElements.Cannon
import gameElements.FirePattern
import gameElements.ShootInDirections
import java.awt.Color
import kotlin.random.Random

fun initCannons() {
    val c = Cannon(600, 100)
    c.firePatterns.add(with(ShootInDirections(c)) {
        nOfDirections = 10
        color = Color.YELLOW
        randomDirections = true
        this
    })
    cannons.add(c)
}
