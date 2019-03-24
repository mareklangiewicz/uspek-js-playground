package app

import react.*
import react.dom.*
import playground.playground

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("App-header") {
            h2 { +"Welcome to USpek Playground" }
        }
        playground()
    }
}

fun RBuilder.app() = child(App::class) {}
