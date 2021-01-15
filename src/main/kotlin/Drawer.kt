import gameElements.Bullet
import gameElements.Cannon
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.JPanel
import kotlin.random.Random

class Drawer: JPanel() {
    init {
        background = Color.BLACK
        for (i in 5..100 step 5) {
            for (j in 5..100 step 5) {
                val b = Bullet(i, j)
                b.velocity = Point(Random.nextInt(-5, 5), Random.nextInt(-5, 5))
                bullets.add(b)
            }
        }
        val c = Cannon(100, 100)
        c.firePatterns.add lambda@{
            if (timer % 60 != 0) return@lambda
            val bull = Array(5) { Bullet(100, 100) }
            for (i in bull.indices) {
                i.toFloat()
                bull[i].velocity = Point(Random.nextInt(-5, 5), Random.nextInt(-5, 5))
            }
            bullets.addAll(bull)
        }
        cannons.add(c)
    }

    override fun paintComponent(g: Graphics?) {
        (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF)
        bullets.forEach { it.move(); it.display(g) }
        cannons.forEach { it.fire(); it.display(g) }
        bullets.removeAll { it.isOffScreen() }
        mainFrame.repaint()
        timer++
    }
}