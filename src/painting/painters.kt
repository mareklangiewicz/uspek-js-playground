package painting

import org.w3c.dom.CanvasRenderingContext2D as Ctx
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.random.Random

const val WIDTH = 800.0
const val HEIGHT = 800.0

private val ctx by lazy {
    val canvas = document.getElementsByTagName("canvas")[0] as HTMLCanvasElement
    canvas.width  = WIDTH.toInt()
    canvas.height = HEIGHT.toInt()
    canvas.getContext("2d") as Ctx
}

infix fun Int.rnd(to: Int) = (this .. to).random()
infix fun Double.rnd(to: Double) = Random.nextDouble(this, to)

val Int.near get() = this - this / 6 rnd this + this / 6
val Double.near get() = this - this / 6 rnd this + this / 6

private val rndx get() = 0.0 rnd WIDTH
private val rndy get() = 0.0 rnd HEIGHT
private val rndhue get() = 0 rnd 360

fun paintSomething() = ctx.run {
    repeat(10) { randomCurve() }
}

fun clearCanvas() = ctx.clearRect(0.0, 0.0, WIDTH, HEIGHT)

private fun Ctx.randomCurve() {
    beginPath()
    lineWidth = 1.0 rnd 15.0
    moveTo(rndx, rndy)
    bezierCurveTo(rndx, rndy, rndx, rndy, rndx, rndy)
    strokeStyle = "hsl($rndhue, 50%, 50%)"
    stroke()
}