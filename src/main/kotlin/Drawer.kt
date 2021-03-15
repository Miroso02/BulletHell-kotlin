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
                if (e == null || player.isDead) return
                val curPosition = Point(e.point.x, e.point.y)
                player.position = curPosition
            }
        })
        background = Color.BLACK
    }

    override fun paintComponent(g: Graphics?) {
        (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF)
        updateScreen(g)
        mainFrame.repaint()
    }
}