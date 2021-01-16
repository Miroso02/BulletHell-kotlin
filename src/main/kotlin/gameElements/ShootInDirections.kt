package gameElements

import Point
import bullets
import timer
import java.awt.Color
import kotlin.math.sqrt
import kotlin.random.Random

open class ShootInDirections(cannon: Cannon): FirePattern(cannon) {
    private val directions = ArrayList<Point>()
    var speed = 3f
    var color: Color = Color.WHITE
    var size = 10
    var delay = 30
    var nOfDirections = 0
    var randomDirections = false
    override fun fire() {
        if (timer % delay != 0) return
        if (randomDirections)
            setDirections(*Array(nOfDirections) {
                Point(Random.nextFloat() * 2 - 1, Random.nextFloat() * 2 - 1)
            })
        val bul = Array(directions.size) { Bullet(cannon.position) }
        for (i in bul.indices) {
            bul[i].color = color
            bul[i].velocity = directions[i] * speed
            bul[i].size = size
        }
        bullets.addAll(bul)
    }
    fun setDirections(vararg directions: Point) {
        this.directions.clear()
        for (dir in directions) {
            val norm = sqrt(dir.x * dir.x + dir.y * dir.y)
            dir.x /= norm
            dir.y /= norm
            this.directions.add(dir)
        }
        nOfDirections = directions.size
    }
}