package painting

import org.w3c.dom.CanvasRenderingContext2D as Ctx
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.random.Random

const val WIDTH = 800
const val HEIGHT = 800

private val ctx by lazy {
    val canvas = document.getElementsByTagName("canvas")[0] as HTMLCanvasElement
    canvas.width  = WIDTH
    canvas.height = HEIGHT
    canvas.getContext("2d") as Ctx
}

infix fun Int.randomTo(to: Int) = (this .. to).random()
infix fun Int.randomUntil(until: Int) = (this until until).random()

infix fun Double.randomUntil(to: Double) = Random.nextDouble(this, to)

fun around(value: Int) = value - value / 6 randomTo value + value / 6
fun around(value: Double) = value - value / 6 randomUntil value + value / 6

fun paintSomething() = ctx.run {
//    fillStyle = "rgb(200, 0, 0, 0.2)"
//    fillRect(around(10.0), around(10.0), around(50.0), around(50.0))
//    fillStyle = "rgb(0, 0, 200, 0.3)"
//    fillRect(around(30.0), around(30.0), around(50.0), around(50.0))
    paintCurve()
}

fun paintClear() = ctx.clearRect(0.0, 0.0, WIDTH.toDouble(), HEIGHT.toDouble())

private var hue = 0

private fun Ctx.paintCurve() {
    beginPath()
    lineWidth = around(20.0)
    moveTo(around(300.0), around(300.0))
    bezierCurveTo(
        around(500.0),
        around(300.0),
        around(around(600.0)),
        around(400.0),
        around(500.0),
        around(500.0)
    )
    hue += around(30)
    strokeStyle = "hsl($hue, 50%, 50%"
    stroke()
}