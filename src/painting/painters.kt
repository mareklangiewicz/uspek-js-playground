package painting

import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.math.PI
import kotlin.random.Random
import org.w3c.dom.CanvasRenderingContext2D as Ctx

const val WIDTH = 800.0
const val HEIGHT = 800.0

fun paintSomething() = Kandinsky.paint()

interface Painter {
    val name: String
    fun paint()
}

object Kandinsky : Painter {
    override val name = "Wassily Kandinsky"
    override fun paint() = ctx.run {
        randomPainting() // TODO
    }
}

object Pollock : Painter {
    override val name = "Jackson Pollock"
    override fun paint() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

object Picasso : Painter {
    override val name = "Pablo Picasso"
    override fun paint() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private val ctx by lazy {
    val canvas = document.getElementsByTagName("canvas")[0] as HTMLCanvasElement
    canvas.width = WIDTH.toInt()
    canvas.height = HEIGHT.toInt()
    canvas.getContext("2d") as Ctx
}

infix fun Int.rnd(to: Int) = Random.nextInt(this, to + 1)
infix fun Double.rnd(to: Double) = Random.nextDouble(this, to)

val Int.near get() = this - this / 6 rnd this + this / 6
val Double.near get() = this - this / 6 rnd this + this / 6

private val rndx get() = 0.0 rnd WIDTH
private val rndy get() = 0.0 rnd HEIGHT

private fun Ctx.randomPainting() {
    repeat(4) { randomCurve(100, 140) }
    repeat(10) { randomCurve(1, 30) }
    repeat(4) { randomCircle(4, 10) }
    repeat(3) { randomPolyline(4, 4, 10) }
}

fun clearCanvas() = ctx.clearRect(0.0, 0.0, WIDTH, HEIGHT)

private fun Ctx.randomCircle(minWidth: Int, maxWidth: Int = minWidth, minHue: Int = 0, maxHue: Int = 360) = strokePath {
    val radius = 40.0 rnd (100.0 rnd 300.0)
    arc(rndx, rndy, radius, 0.0, 2 * PI)
    lineWidth = (minWidth rnd maxWidth).toDouble()
    strokeStyle = "hsl(${minHue rnd maxHue}, 60%, 50%)"
}

private fun Ctx.randomCurve(minWidth: Int, maxWidth: Int = minWidth, minHue: Int = 0, maxHue: Int = 360) = strokePath {
    moveTo(rndx, rndy)
    bezierCurveTo(rndx, rndy, rndx, rndy, rndx, rndy)
    lineWidth = (minWidth rnd maxWidth).toDouble()
    strokeStyle = "hsl(${minHue rnd maxHue}, 60%, 50%)"
}

private fun Ctx.randomPolyline(segments: Int = 1, minWidth: Int = 4, maxWidth: Int = minWidth, minHue: Int = 0, maxHue: Int = 360) = strokePath {
    moveTo(rndx, rndy)
    repeat(segments) { lineTo(rndx, rndy) }
    lineWidth = (minWidth rnd maxWidth).toDouble()
    strokeStyle = "hsl(${minHue rnd maxHue}, 60%, 50%)"
}

private inline fun Ctx.strokePath(block: Ctx.() -> Unit) {
    beginPath()
    block()
    stroke()
}
