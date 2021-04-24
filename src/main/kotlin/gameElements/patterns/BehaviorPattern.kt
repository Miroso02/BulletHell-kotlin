package gameElements.patterns

import timer

open class BehaviorPattern<T : PatternComponent<T>> {
    open operator fun invoke(context: T) {
        val (obj) = context
        val endOfCurFn = fns.nextKey(obj.curMovFuncInd)
        if (timer - obj.createTime > endOfCurFn) {
            obj.curMovFuncInd = endOfCurFn
        }
        fns[endOfCurFn]?.invoke(context)
    }

    protected val fns = KeyOrderedHashMap<T>()

    open fun then(time: Int, f: (T) -> Unit): BehaviorPattern<T> =
        this.apply { fns[fns.lastKey() + time] = f }

    open fun then(mp: BehaviorPattern<T>): BehaviorPattern<T> =
        this.apply {
            val lastTime = fns.lastKey()
            mp.fns.forEach { time, fn -> fns[lastTime + time] = fn }
        }

    fun copy() = BehaviorPattern<T>().also { it.fns.putAll(this.fns) }
    fun repeat(n: Int = 1) =
        this.apply {
            val copy = this.copy()
            repeat(n) { this.then(copy) }
        }
}