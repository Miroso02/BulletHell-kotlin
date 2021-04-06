package gameElements

class KeyOrderedHashMap : HashMap<Int, (GameObject, Int) -> Unit>() {
    private val nextKeys = HashMap<Int, Int>()
    private var lastKey = 0

    fun nextKey(key: Int) = nextKeys[key] ?: 0
    fun lastKey() = lastKey
    override fun put(key: Int, value: (GameObject, Int) -> Unit): ((GameObject, Int) -> Unit)? {
        nextKeys[lastKey] = key
        lastKey = key
        return super.put(key, value)
    }

    fun putAll(from: KeyOrderedHashMap) {
        this.lastKey = from.lastKey
        this.nextKeys.putAll(from.nextKeys)
        super.putAll(from)
    }
}