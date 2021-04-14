package gameElements.patterns

import gameElements.GameObject
import timer

class LoopedMovePattern : MovePattern() {
    private var totalTime = 0
    override operator fun invoke(obj: GameObject, i: Int) {
        val endOfCurFn = fns.nextKey(obj.curMovFuncInd)
        ((timer - obj.createTime) % totalTime).let {
            if (it > endOfCurFn) {
                obj.curMovFuncInd = endOfCurFn
            }
            else if (it == 0 && endOfCurFn == fns.lastKey()) {
                obj.curMovFuncInd = 0
            }
        }

        fns[endOfCurFn]?.invoke(obj, i)
    }

    override fun then(time: Int, f: (GameObject, Int) -> Unit): MovePattern {
        totalTime += time
        return super.then(time, f)
    }

    override fun then(time: Int, f: (GameObject) -> Unit): MovePattern {
        totalTime += time
        return super.then(time, f)
    }

    override fun then(mp: MovePattern): MovePattern {
        val newMp = super.then(mp)
        totalTime = fns.lastKey()
        return newMp
    }
}