package gameElements
//
//import timer
//
//class MovePattern() {
//    private var fn: (GameObject, Int) -> Unit = { _, _ -> }
//    private var totalTime = 0
//
//    constructor(fn: (GameObject, Int) -> Unit): this() { this.fn = fn }
//    constructor(mp: MovePattern): this(mp.fn)
//    private constructor(totalTime: Int, fn: (GameObject, Int) -> Unit): this(fn) { this.totalTime = totalTime }
//
//    operator fun invoke(obj: GameObject, i: Int) = fn(obj, i)
//    operator fun invoke(obj: GameObject) = fn(obj, 0)
//
//    fun then(time: Int, f: (GameObject, Int) -> Unit) =
//        MovePattern(totalTime + time) { _, _ ->  }
//            .also { it.fn = { obj, i -> if (timer - obj.createTime < it.totalTime - time) fn(obj, i) else f(obj, i) } }
//    fun then(time: Int, f: (GameObject) -> Unit) =
//        MovePattern(totalTime + time) { _, _ ->  }
//            .also { it.fn = { obj, i -> if (timer - obj.createTime < it.totalTime - time) fn(obj, i) else f(obj) } }
//    fun then(time: Int, mp: MovePattern) =
//        MovePattern(totalTime + time) { _, _ ->  }
//            .also { it.fn = { obj, i -> if (timer - obj.createTime < it.totalTime - time) fn(obj, i) else mp.fn(obj, i) } }
//    fun then(mp: MovePattern): MovePattern {
//        val time = mp.totalTime
//        mp.totalTime += totalTime
//        return MovePattern(mp.totalTime) { _, _ -> }
//            .also {
//                it.fn = { obj, i ->
//                    if (timer - obj.createTime < it.totalTime - time) fn(obj, i)
//                    else mp.fn(obj, i)
//                }
//            }
//    }
//}
