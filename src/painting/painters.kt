package painting

import org.w3c.dom.CanvasRenderingContext2D as Ctx
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.get
import org.w3c.dom.mediacapture.DoubleRange
import kotlin.browser.document
import kotlin.js.Math.random
import kotlin.random.Random

const val WIDTH = 800.0
const val HEIGHT = 800.0

private val ctx by lazy {
    val canvas = document.getElementsByTagName("canvas")[0] as HTMLCanvasElement
    canvas.width  = WIDTH.toInt()
    canvas.height = HEIGHT.toInt()
    canvas.getContext("2d") as Ctx
}

infix fun Int.randomTo(to: Int) = (this .. to).random()
infix fun Int.randomUntil(until: Int) = (this until until).random()

infix fun Double.randomUntil(to: Double) = Random.nextDouble(this, to)

val Int.near get() = this - this / 6 randomTo this + this / 6
val Double.near get() = this - this / 6 randomUntil this + this / 6

private val rndx get() = Random.nextDouble(0.0, WIDTH)
private val rndy get() = Random.nextDouble(0.0, HEIGHT)

fun paintSomething() = ctx.run {
//    fillStyle = "rgb(200, 0, 0, 0.2)"
//    fillRect(near(10.0), near(10.0), near(50.0), near(50.0))
//    fillStyle = "rgb(0, 0, 200, 0.3)"
//    fillRect(near(30.0), near(30.0), near(50.0), near(50.0))
    paintCurve()
}

fun paintClear() = ctx.run {
    clearRect(0.0, 0.0, WIDTH.toDouble(), HEIGHT.toDouble())
}

private var hue = 0

private fun Ctx.paintCurve() {
    beginPath()
    lineWidth = 20.0.near.near.near.near.near
    moveTo(rndx, rndy)
    bezierCurveTo(rndx, rndy, rndx, rndy, rndx, rndy)
    bezierCurveTo(rndx, rndy, rndx, rndy, rndx, rndy)
    hue += 30.near.near
    strokeStyle = "hsl($hue, 50%, 50%)"
    stroke()
}