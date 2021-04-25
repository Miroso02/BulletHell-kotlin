package gameElements.behaviorPattern

import gameElements.components.patternComponents.PatternComponent
import timer

open class BehaviorPattern<T : PatternComponent<T>> {
    open operator fun invoke(context: T) {
        val endOfCurFn = fns.nextKey(context.curFunIndex)
        if (timer - context.createTime > endOfCurFn) {
            context.curFunIndex = endOfCurFn
        }
        fns[endOfCurFn]?.invoke(context)
    }

    protected val fns = KeyOrderedHashMap<T>()

    open fun then(time: Int = 100000, f: (T) -> Unit): BehaviorPattern<T> =
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