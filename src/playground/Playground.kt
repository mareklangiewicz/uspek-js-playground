package playground

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import kotlin.browser.*

interface PlaygroundProps : RProps { var speed: Int }
interface PlaygroundState : RState { var tree: String } // FIXME: we will keep whole uspek tree here

class Playground(props: PlaygroundProps) : RComponent<PlaygroundProps, PlaygroundState>(props) {

    private var timerId: Int? = null

    override fun PlaygroundState.init(props: PlaygroundProps) { tree = "click to start/stop: " } // FIXME

    override fun componentDidMount() { start() }
    override fun componentWillUnmount() { stop() }

    override fun RBuilder.render() {
        p {
            attrs { onClickFunction = { toggle() } }
            +"Tree: ${state.tree}"
        }
    }

    private fun toggle() = if (timerId == null) start() else stop()

    private fun start() {
        stop()
        timerId = window.setInterval({
            // actually, the operation is performed on a state's copy, so it stays effectively immutable
            setState { tree += "x" }
        }, props.speed)
    }

    private fun stop() {
        timerId?.let { window.clearInterval(it) }
        timerId = null
    }
}

fun RBuilder.playground(speed: Int = 400) = child(Playground::class) { attrs.speed = speed }
