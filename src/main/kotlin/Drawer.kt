import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel

class Drawer: JPanel() {
    init {
        addMouseMotionListener(object: MouseAdapter() {
            override fun mouseMoved(e: MouseEvent?) {
                if (e == null) return
                if (mouse.x == 0f)
                    mouse = Point(e.point.x, e.point.y)
                else {
                    val curPosition = Point(e.point.x, e.point.y)
                    player.position = curPosition
                    mouse = curPosition
                }
            }
        })
        background = Color.BLACK
    }

    override fun paintComponent(g: Graphics?) {
        (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF)
        bullets.forEach { it.move(); it.display(g) }
        cannons.forEach { it.fire(); it.display(g) }
        bullets.removeAll { it.isOffScreen() }
        player.display(g)
        player.fire()
        playerBullets.forEach { it.move(); it.display(g) }
        playerBullets.removeAll { it.isOffScreen() }
        mainFrame.repaint()
        timer++
    }
}