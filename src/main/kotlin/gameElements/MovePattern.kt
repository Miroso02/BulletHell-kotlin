package gameElements

import timer

// TODO: Make it generic (?)
open class MovePattern {
    private val fns = KeyOrderedHashMap()
    operator fun invoke(obj: GameObject, i: Int) {
        val endOfCurFn = fns.nextKey(obj.curMovFuncInd)
        if (timer - obj.createTime > endOfCurFn) {
            obj.curMovFuncInd = endOfCurFn
        }
        fns[endOfCurFn]?.invoke(obj, i)
    }

    operator fun invoke(obj: GameObject) = this(obj, 0)

    fun then(time: Int, f: (GameObject, Int) -> Unit) =
            this.apply { fns[fns.lastKey() + time] = f }

    fun then(time: Int, f: (GameObject) -> Unit) =
            this.apply { fns[fns.lastKey() + time] = { obj, _ -> f(obj) } }

    fun then(mp: MovePattern) =
            this.apply {
                mp.fns.forEach { (time, fn) -> fns[fns.lastKey() + time] = fn }
            }

    fun copy() = MovePattern().also { it.fns.putAll(this.fns) }
}