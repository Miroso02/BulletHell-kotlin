class Point(var x: Float, var y: Float) {
    constructor(p: Point): this(p.x, p.y)
    constructor(x: Number, y: Number): this(x.toFloat(), y.toFloat())
    operator fun component1() = x
    operator fun component2() = y
    operator fun unaryMinus() = Point(-x, -y)
    operator fun plus(p: Point) = Point(x + p.x, y + p.y)
    operator fun minus(p: Point) = this + -p
}