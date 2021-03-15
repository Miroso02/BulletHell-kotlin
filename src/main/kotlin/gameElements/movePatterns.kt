package gameElements

import Point

val moveForward = { obj: GameObject, _: Int -> obj.position += obj.velocity }
val moveWithAccel = { obj: GameObject, _: Int -> obj.position += obj.velocity; obj.velocity += obj.accel }
val stand = { _: GameObject, _: Int ->  }

fun moveTo(target: () -> Point, time: Int) =
    MovePattern()
        .then(1) { obj ->
            val vec = target() - obj.position
            obj.velocity = vec.norm() * (2f * vec.mag() / time)
            obj.accel = vec.norm() * (-2f * vec.mag() / time / time)
        }
        .then(time, moveWithAccel)