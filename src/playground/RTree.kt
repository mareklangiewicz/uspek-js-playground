package playground

import react.*
import react.dom.*
import uspek.*

fun RBuilder.rtree(tree: USpekTree) {
    div(classes = tree.style) {
        +"${tree.name} ${tree.status}"
        for (branch in tree.branches.values) rtree(branch)
    }

}

private val USpekTree.style get() = if (failed) "tree failure" else if (finished) "tree success" else "tree"
