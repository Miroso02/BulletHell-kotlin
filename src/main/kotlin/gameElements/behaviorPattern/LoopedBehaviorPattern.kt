package gameElements.behaviorPattern

import gameElements.components.patternComponents.PatternComponent
import timer

// TODO: Don't like this class. It makes impossible to inline BehaviorPattern's functions
class LoopedBehaviorPattern<T : PatternComponent<T>> : BehaviorPattern<T>() {
    private var totalTime = 0
    override operator fun invoke(context: T) {
        val endOfCurFn = fns.nextKey(context.curFunIndex)
        ((timer - context.createTime) % totalTime).let {
            if (it > endOfCurFn) {
                context.curFunIndex = endOfCurFn
            }
            else if (it == 0 && endOfCurFn == fns.lastKey()) {
                context.curFunIndex = 0
            }
        }
        fns[endOfCurFn]?.invoke(context)
    }

    override fun then(time: Int, f: (T) -> Unit): BehaviorPattern<T> {
        totalTime += time
        return super.then(time, f)
    }

    override fun then(mp: BehaviorPattern<T>): BehaviorPattern<T> {
        val newMp = super.then(mp)
        totalTime = fns.lastKey()
        return newMp
    }
}