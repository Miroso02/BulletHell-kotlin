package gameElements

import timer

class MovePattern {
    private val fns = LinkedHashMap<Int, (GameObject, Int) -> Unit>()
    operator fun invoke(obj: GameObject, i: Int) {
        for ((time, fn) in fns) {
            if (timer - obj.createTime < time) {
                fn(obj, i)
                break
            }
        }
    }
    operator fun invoke(obj: GameObject) = this(obj, 0)

    fun then(time: Int, f: (GameObject, Int) -> Unit) =
        MovePattern().also { it.fns.putAll(fns); it.fns[(fns.lastKey() ?: 0) + time] = f }
    fun then(time: Int, f: (GameObject) -> Unit) =
        MovePattern().also { it.fns.putAll(fns); it.fns[(fns.lastKey() ?: 0) + time] = { obj, _ -> f(obj) } }
    fun then(mp: MovePattern) =
        MovePattern().also {
            it.fns.putAll(fns)
            val last = fns.lastKey() ?: 0;
            mp.fns.forEach { (time, fn) -> it.fns[last + time] = fn }
        }

    private fun <T, V> LinkedHashMap<T, V>.lastKey(): T? {
        var lastKey: T? = null
        this.forEach { (i, _) -> lastKey = i }
        return lastKey
    }
}