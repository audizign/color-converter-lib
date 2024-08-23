package dev.idot.text.color

class Color {
    companion object {
        // some things just dont need comments
        /**
         * @return the closest named [Color] in [Colors] to this [Color]
         */
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

        /**
         * If you want to throw an exception on invalid input, use [Color] constructor instead
         * @return a [Color] or null if the [String] is not a valid hex color
         */
        fun String.colorFromHex(): Color? {
            return try { Color(this) } catch (ex: IllegalArgumentException) { return null }
        }

        fun String.findMojangColor(): Color? {
            if (this.length == 2) return Color(this[1])
            drop(3).split(MC_COLOR_DELIM).joinToString("").let {
                return runCatching { Color(it.toInt(16)) }.getOrNull()
            }
        }
    }

    private var _colorCode: Char? = null
    val colorCode: Char
        get() {
            _colorCode?.let { return it }
            var distance = 0x7FFFFFFF
            var closest = 'f'
            for (c in ColorCode.codes()) {
                val d = distance(Color(c))
                if (d >= distance) continue
                distance = d
                closest = c.lowercaseChar()
                if (d == 0) {
                    isExactCode = true
                    break
                }
            }
            _colorCode = closest
            return closest
        }

    var isExactCode: Boolean = false
        private set
        get() {
            if (!field) colorCode
            return field
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

    @Throws(IllegalArgumentException::class)
    constructor(value: String) {
        val match = hexColorRegex.find(value) ?: throw IllegalArgumentException("Invalid hex code: $value")
        match.groupValues[1].let { hex ->
            val formatted = if (hex.length == 3) buildString {
                append(hex[0]).append(hex[0]).append(hex[1]).append(hex[1]).append(hex[2]).append(hex[2])
            } else hex
            this.rgb = formatted.toIntOrNull(16)?.let(::Color)!!.rgb
        }
    }

    private fun distance(color: Color): Int {
        val r = color.red - red
        val g = color.green - green
        val b = color.blue - blue
        return r*r + g*g + b*b
    }

    /**
     * @return the hex representation as "RRGGBB"
     */
    fun hex(): String = "%06X".format(rgb).lowercase()

    /**
     * @return the hex representation as "§x§R§R§G§G§B§B"
     */
    fun hexMojang(): String = buildString {
        append(MC_COLOR_DELIM).append("x")
        hex().forEach {
            append(MC_COLOR_DELIM).append(it)
        }
    }.lowercase()

    fun hexBukkit(): String = buildString(14) {
        append("&x")
        hex().forEach { append("&").append(it) }
    }

    /**
     * @return the smallest [String] representation as either a color code ("§C") or hex code ("§x§R§R§G§G§B§B")
     */
    fun minify(): String = if (isExactCode) "$MC_COLOR_DELIM$colorCode" else hexMojang()
}
