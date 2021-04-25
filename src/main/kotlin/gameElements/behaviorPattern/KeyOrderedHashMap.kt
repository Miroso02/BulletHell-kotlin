package gameElements.behaviorPattern

import gameElements.components.BehaviorComponent

class KeyOrderedHashMap<T : BehaviorComponent> : HashMap<Int, (T) -> Unit>() {
    private val nextKeys = HashMap<Int, Int>()
    private var lastKey = 0

    fun nextKey(key: Int) = nextKeys[key] ?: 0
    fun lastKey() = lastKey
    override fun put(key: Int, value: (T) -> Unit): ((T) -> Unit)? {
        nextKeys[lastKey] = key
        lastKey = key
        return super.put(key, value)
    }

    fun putAll(from: KeyOrderedHashMap<T>) {
        this.lastKey = from.lastKey
        this.nextKeys.putAll(from.nextKeys)
        super.putAll(from)
    }

    fun forEach(action: (Int, (T) -> Unit) -> Unit) {
        var key = nextKey(0)
        while (true) {
            action(key, this[key]!!)
            if (key == lastKey) break
            key = nextKey(key)
        }
    }
}