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
        setSize(1500, 1000)
        title = "Bullet hell"
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}