package gameElements

import Point

val moveForward = { obj: GameObject, _: Int -> obj.position += obj.velocity }
val moveWithAccel = { obj: GameObject, _: Int -> obj.position += obj.velocity; obj.velocity += obj.accel }
val stand = { _: GameObject, _: Int -> }

inline fun moveTo(crossinline target: (GameObject, Int) -> Point, time: Int) =
        MovePattern()
                .then(1) { obj, i ->
                    val vec = target(obj, i) - obj.position
                    val mag = vec.mag()
                    val norm = vec * (1 / mag)
                    obj.velocity = norm * (2f * mag / time)
                    obj.accel = norm * (-2f * mag / time / time)
                }
                .then(time, moveWithAccel)