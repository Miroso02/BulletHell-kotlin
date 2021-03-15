package gameElements

class NewFirePattern(val fn: (Bullet?, Int) -> Bullet?) {
    constructor(n: Int): this({ _, _ -> Bullet() })
    operator fun invoke(b: Bullet, i: Int) = fn(b, i)

    inline fun applyIndexed(crossinline f: (Bullet, Int) -> Bullet?) =
        NewFirePattern { b, i -> fn(b, i)?.apply { f(this, i) } }
    inline fun apply(crossinline f: (Bullet) -> Unit) =
        NewFirePattern { b, i -> fn(b, i)?.apply(f) }
}