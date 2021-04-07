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
var timer = -1

fun main() {
    MainFrame
    initCannons()
}

object MainFrame: JFrame() {
    init {
        val drawer = object: JPanel() {
            init {
                addMouseMotionListener(object: MouseAdapter() {
                    override fun mouseMoved(e: MouseEvent?) {
                        if (e == null || Player.isDead) return
                        val curPosition = Point(e.point.x, e.point.y)
                        Player.position = curPosition
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
        cannons.forEach { it.update(g) }
        timer++
    }
    catch (e: ConcurrentModificationException) {e.printStackTrace()}
}