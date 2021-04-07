package gameElements

import timer

// TODO: Make it generic (?)
open class MovePattern {
    protected val fns = KeyOrderedHashMap()

    open operator fun invoke(obj: GameObject, i: Int) {
        val endOfCurFn = fns.nextKey(obj.curMovFuncInd)
        if (timer - obj.createTime > endOfCurFn) {
            obj.curMovFuncInd = endOfCurFn
        }
        fns[endOfCurFn]?.invoke(obj, i)
    }

    operator fun invoke(obj: GameObject) = this(obj, 0)

    open fun then(time: Int, f: (GameObject, Int) -> Unit): MovePattern =
            this.apply { fns[fns.lastKey() + time] = f }

    open fun then(time: Int, f: (GameObject) -> Unit): MovePattern =
            this.apply { fns[fns.lastKey() + time] = { obj, _ -> f(obj) } }

    open fun then(mp: MovePattern): MovePattern =
            this.apply {
                val lastTime = fns.lastKey()
                mp.fns.forEach { time, fn -> fns[lastTime + time] = fn }
            }

    fun copy() = MovePattern().also { it.fns.putAll(this.fns) }
    fun repeat(n: Int = 1) =
            this.apply {
                val copy = this.copy()
                repeat(n) { this.then(copy) }
            }
}