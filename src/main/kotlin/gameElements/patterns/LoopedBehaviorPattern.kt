package gameElements.patterns

import timer

// TODO: Don't like this class. It makes impossible to inline BehaviorPattern's functions
class LoopedBehaviorPattern<T : PatternComponent<T>> : BehaviorPattern<T>() {
    private var totalTime = 0
    override operator fun invoke(context: T) {
        val (obj) = context
        val endOfCurFn = fns.nextKey(obj.curMovFuncInd)
        ((timer - obj.createTime) % totalTime).let {
            if (it > endOfCurFn) {
                obj.curMovFuncInd = endOfCurFn
            }
            else if (it == 0 && endOfCurFn == fns.lastKey()) {
                obj.curMovFuncInd = 0
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