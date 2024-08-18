package dev.idot.colorparser.lib

class Color {
    companion object {
        fun Color.closestNamed(): Colors {
            var distance = 0x7FFFFFFF
            var closest: Colors? = null
            for (key in Colors.entries) {
                val d = this.distance(key())
                if (d >= distance) continue
                distance = d
                closest = key
                if (distance == 0) break
            }
            return closest!!
        }
    }

    private var _colorCode: Char? = null
    val colorCode: Char
        get() = _colorCode ?: run {
            var distance = 0x7FFFFFFF
            var closest = 'f'
            for (c in ColorCode.entries.map { it.code }) {
                val d = distance(Color(c))

                if (d >= distance) continue
                distance = d
                closest = c.lowercaseChar()

                if (d != 0) continue
                isExactCode = true
                break
            }
            _colorCode = closest
            closest
        }
    var isExactCode: Boolean = false
        private set
        get() = field || run { // Yes, this is necessary
            colorCode
            field
        }


    var rgb: Int = 0
        private set

    val red: Int by lazy { rgb shr 16 and 0xFF }
    val green: Int by lazy { rgb shr 8 and 0xFF }
    val blue: Int by lazy { rgb and 0xFF }

    constructor(value: Int) {
        this.rgb = value.coerceIn(0, 0xFFFFFF)
    }

    @Throws(IllegalArgumentException::class)
    constructor(value: Char) {
        val colorCode = ColorCode.entries.find { it.code == value.lowercaseChar() }
            ?: throw IllegalArgumentException("Invalid color code: $value")
        this.rgb = colorCode.rgb
        this._colorCode = colorCode.code
        this.isExactCode = true
    }

    constructor(red: Int, green: Int, blue: Int) {
        this.rgb = (red.coerceIn(0, 255) shl 16) + (green.coerceIn(0, 255) shl 8) + blue.coerceIn(0, 255)
    }

    private fun distance(color: Color): Int {
        val r = color.red - red
        val g = color.green - green
        val b = color.blue - blue
        return r*r + g*g + b*b
    }

    fun hex(): String {
        return String.format("%06X", rgb).lowercase()
    }

    fun hexMojang(): String {
        return buildString {
            append(COLOR_CHAR)
            append("x")
            hex().forEach {
                append(COLOR_CHAR)
                append(it)
            }
        }.lowercase()
    }

    fun hexBukkit(): String {
        return buildString {
            append("&x")
            hex().forEach { append("&$it") }
        }
    }

    fun minified(): String {
        return if (isExactCode) "$COLOR_CHAR$colorCode" else hexMojang()
    }
}
