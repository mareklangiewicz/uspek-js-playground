package playground

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import uspek.*
import kotlinx.coroutines.*

interface PlaygroundProps : RProps { var speed: Int }
interface PlaygroundState : RState { var tree: USpekTree }

class Playground(props: PlaygroundProps) : RComponent<PlaygroundProps, PlaygroundState>(props) {

    override fun PlaygroundState.init(props: PlaygroundProps) {
        tree = uspekContext.root
        uspekLog = {
            println(it.status)
            setState { tree = uspekContext.root }
            delay(200)
        }
    }

    override fun componentDidMount() {
        GlobalScope.launch { example() }
    }
    override fun componentWillUnmount() { }

    override fun RBuilder.render() { rtree(state.tree) }
}

fun RBuilder.playground(speed: Int = 400) = child(Playground::class) { attrs.speed = speed }
