import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

class Point(var x: Float, var y: Float) {
    companion object {
        fun randVector() = with(Random.nextFloat() * Math.PI * 2) { Point(cos(this), sin(this)) }
    }
    constructor(p: Point): this(p.x, p.y)
    constructor(x: Number, y: Number): this(x.toFloat(), y.toFloat())
    operator fun component1() = x
    operator fun component2() = y
    operator fun unaryMinus() = Point(-x, -y)
    operator fun plus(p: Point) = Point(x + p.x, y + p.y)
    operator fun minus(p: Point) = this + -p
    operator fun times(s: Float) = Point(x * s, y * s)
    override fun toString() = "$x $y"
    fun mag() = sqrt(x * x + y * y)
    fun norm() = this * (1 / mag())
}