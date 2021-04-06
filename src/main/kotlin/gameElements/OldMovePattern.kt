package gameElements

import timer

class OldMovePattern() {
    private var fn: (GameObject, Int) -> Unit = { _, _ -> }
    private var totalTime = 0

    constructor(fn: (GameObject, Int) -> Unit): this() { this.fn = fn }
    constructor(mp: OldMovePattern): this(mp.fn)
    private constructor(totalTime: Int): this() { this.totalTime = totalTime }

    operator fun invoke(obj: GameObject, i: Int) = fn(obj, i)
    operator fun invoke(obj: GameObject) = fn(obj, 0)

    fun then(time: Int, f: (GameObject, Int) -> Unit) =
        OldMovePattern(totalTime + time)
            .also { it.fn = { obj, i -> if (timer - obj.createTime < it.totalTime - time) fn(obj, i) else f(obj, i) } }
    fun then(time: Int, f: (GameObject) -> Unit) =
        OldMovePattern(totalTime + time)
            .also { it.fn = { obj, i -> if (timer - obj.createTime < it.totalTime - time) fn(obj, i) else f(obj) } }
    fun then(time: Int, mp: OldMovePattern) =
        OldMovePattern(totalTime + time)
            .also { it.fn = { obj, i -> if (timer - obj.createTime < it.totalTime - time) fn(obj, i) else mp.fn(obj, i) } }
    fun then(mp: OldMovePattern): OldMovePattern {
        val time = mp.totalTime
        mp.totalTime += totalTime
        return OldMovePattern(mp.totalTime)
            .also {
                it.fn = { obj, i ->
                    if (timer - obj.createTime < it.totalTime - time) fn(obj, i)
                    else mp.fn(obj, i)
                }
            }
    }
}
