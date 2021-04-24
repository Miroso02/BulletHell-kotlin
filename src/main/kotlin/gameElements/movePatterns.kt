package gameElements

import Point
import gameElements.patterns.BehaviorComponent
import gameElements.patterns.BehaviorPattern
import gameElements.patterns.MoveComponent

// moving functions
val moveForward: (MoveComponent) -> Unit = { context ->
    val (obj) = context
    obj.position += context.velocity
}
val moveWithAccel: (MoveComponent) -> Unit = { context ->
    val (obj) = context
    obj.position += context.velocity
    context.velocity += context.accel
}
val stand: (BehaviorComponent) -> Unit = { _ -> }

inline fun moveTo(time: Int, crossinline target: (BehaviorComponent) -> Point) =
        BehaviorPattern<MoveComponent>()
                .then(1) { context ->
                    val vec = target(context) - context.obj.position
                    val mag = vec.mag()
                    val norm = vec * (1 / mag)
                    context.velocity = norm * (2f * mag / time)
                    context.accel = norm * (-2f * mag / time / time)
                }
                .then(time, moveWithAccel)