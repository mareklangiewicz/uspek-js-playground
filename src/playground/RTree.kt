package playground

import react.*
import react.dom.*
import uspek.*

fun RBuilder.rtree(tree: USpekTree) {
    div(classes = tree.classes) {
        +tree.title
        for (branch in tree.branches.values) rtree(branch)
    }

}

private val USpekTree.title get() = if (failed) "$name .. FAILURE" else if (finished) "$name .. SUCCESS" else "$name .."

private val USpekTree.classes get() = if (failed) "tree failure" else if (finished) "tree success" else "tree"
