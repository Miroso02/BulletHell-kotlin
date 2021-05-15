package gameElements.patterns

import Point
import gameElements.behaviorPattern.BehaviorPattern
import gameElements.components.*
import gameElements.components.patternComponents.UniversalPatternComponent
import gameElements.elements.BodyElement

// moving functions
val moveForward: (UniversalPatternComponent) -> Unit = {
    (it.context["body"] as BodyElement).position += it.context["velocity"] as Point
}
val moveWithAccel: (UniversalPatternComponent) -> Unit = { (context) ->
    val body: BodyElement by context
    var velocity: Point by context
    val accel: Point by context
    body.position += velocity
    velocity = velocity + accel
}
val stand: (BehaviorComponent) -> Unit = { _ -> }

fun moveTo(time: Int, target: (UniversalPatternComponent) -> Point) =
        BehaviorPattern<UniversalPatternComponent>()
                .then(1) {
                    val body: BodyElement by it.context
                    val vec = target(it) - body.position
                    val mag = vec.mag()
                    val norm = vec * (1 / mag)
                    it.context["velocity"] = norm * (2f * mag / time)
                    it.context["accel"] = norm * (-2f * mag / time / time)
                }
                .then(time, moveWithAccel)

