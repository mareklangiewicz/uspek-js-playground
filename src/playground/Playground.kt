package playground

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import uspek.*

interface PlaygroundProps : RProps { var speed: Int }
interface PlaygroundState : RState { var tree: USpekTree }

class Playground(props: PlaygroundProps) : RComponent<PlaygroundProps, PlaygroundState>(props) {

    override fun PlaygroundState.init(props: PlaygroundProps) {
        tree = uspekContext.root
        uspekLog = {
            println(it.status)
            setState { tree = uspekContext.root }
        }
        example()
    }

    override fun componentDidMount() { }
    override fun componentWillUnmount() { }

    override fun RBuilder.render() {
        p {
            attrs { onClickFunction = { } }
            +"Tree: ${state.tree}"
        }
    }
}

fun RBuilder.playground(speed: Int = 400) = child(Playground::class) { attrs.speed = speed }
