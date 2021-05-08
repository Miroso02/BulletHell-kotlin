import gameElements.Bullet
import gameElements.Cannon
import gameElements.Player
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JPanel

val cannons = ArrayList<Cannon>().apply { add(Player) }
val bullets = ArrayList<Bullet>()
val playerBullets = ArrayList<Bullet>()
var timer = -1

fun main() {
    MainFrame
    initCannons()
}

object MainFrame : JFrame() {
    init {
        val drawer = object : JPanel() {
            init {
                addMouseMotionListener(object : MouseAdapter() {
                    override fun mouseMoved(e: MouseEvent?) {
                        if (e == null || Player.healthElement.isDead) return
                        val curPosition = Point(e.point.x, e.point.y)
                        Player.body.position = curPosition
                    }
                })
                background = Color.BLACK
            }

            override fun paintComponent(g: Graphics?) {
                updateScreen(g as Graphics2D)
                MainFrame.repaint()
            }
        }
        add(drawer)
        background = Color.BLACK
        setSize(1500, 1000)
        title = "Bullet hell"
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}

fun updateScreen(g: Graphics2D) {
    try {
        cannons.forEach { it.displayComponent.graphics = g; it.update() }
        bullets.removeAll { it.body.isOffScreen() }
        bullets.forEach {
            it.displayComponent.graphics = g
            it.update()
            if (it.body.collides(Player.body))
                Player.healthElement.health = 0
        }
        playerBullets.forEach {
            it.displayComponent.graphics = g
            it.update()
        }
        playerBullets.removeAll {
            it.body.isOffScreen() ||
                    cannons.any { c ->
                        if (c != Player && !c.healthElement.isDead && c.body.collides(it.body)) {
                            c.healthElement.health--; true
                        } else false
                    }
        }
        timer++
    } catch (e: ConcurrentModificationException) {
        e.printStackTrace()
    }
}
