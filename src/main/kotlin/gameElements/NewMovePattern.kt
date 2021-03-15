package gameElements

import timer

class NewMovePattern(
    private val endTime: Int = 0,
    private var fn: (GameObject, Int) -> Unit = { _, _ -> }
) {
    private var next: NewMovePattern? = null

    operator fun invoke(obj: GameObject, i: Int) = fn(obj, i)
    operator fun invoke(obj: GameObject) = this(obj, 0)

    fun then(time: Int, f: (GameObject, Int) -> Unit): NewMovePattern {
        val next = NewMovePattern(endTime + time, f)
        fn = { obj, i -> if (timer - obj.createTime < endTime) fn(obj, i) else next(obj, i) }
        return next
    }
}