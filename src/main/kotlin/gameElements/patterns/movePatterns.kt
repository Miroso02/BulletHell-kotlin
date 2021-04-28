package gameElements.patterns

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.*
import gameElements.components.patternComponents.DisplayComponent
import gameElements.components.patternComponents.MoveComponent
import gameElements.components.patternComponents.PatternComponent

// moving functions
val moveForward: (MoveComponent) -> Unit = { context ->
    context.position += context.velocity
}
val moveWithAccel: (MoveComponent) -> Unit = { context ->
    context.position += context.velocity
    context.velocity += context.accel
}
val stand: (BehaviorComponent) -> Unit = { _ -> }

inline fun moveTo(time: Int, crossinline target: (PatternComponent<*>) -> Point) =
        BehaviorPattern<MoveComponent>()
                .then(1) { context ->
                    val vec = target(context) - context.position
                    val mag = vec.mag()
                    val norm = vec * (1 / mag)
                    context.velocity = norm * (2f * mag / time)
                    context.accel = norm * (-2f * mag / time / time)
                }
                .then(time, moveWithAccel)

val bulletDisplayPattern = BehaviorPattern<DisplayComponent>()
    .then { context ->
        context.graphics?.let { g ->
            g.color = context.color
            g.fillOval(context.position.x.toInt(), context.position.y.toInt(), context.size, context.size)
        }
    }
val cannonDisplayPattern = BehaviorPattern<DisplayComponent>()
    .then { context ->
        context.graphics?.let { g ->
            g.color = context.color
            val (x, y) = context.position - Point(context.size / 2, context.size / 2)
            g.drawRect(x.toInt(), y.toInt(), context.size, context.size)
        }
    }