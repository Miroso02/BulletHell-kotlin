import java.awt.Color
import javax.swing.JFrame

val mainFrame = Frame()
fun main() {
    initCannons()
}

class Frame: JFrame() {
    init {
        add(Drawer())
        background = Color.BLACK
        setSize(800, 600)
        title = "gameElements.Bullet hell"
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}