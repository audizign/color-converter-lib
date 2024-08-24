package dev.idot.text.color

class Color {
    companion object {
        /**
         * A shorthand for [Color] constructor, but it returns null on invalid input
         * @return the [Color]? of a [String]; null if invalid
         */
        fun String.colorFromHex(): Color? {
            return try {
                Color(this)
            } catch (ex: IllegalArgumentException) {
                null
            }
        }

        /**
         * @return the [Color]? of a mojang color code ("§x§R§R§G§G§B§B")
         */
        fun String.findMojangColor(): Color? {
            if (length == 2) return Color(this[1])
            drop(3).split(MC_COLOR_DELIM).joinToString("").let {
                return runCatching { Color(it.toInt(16)) }.getOrNull()
            }
        }

        /**
         * @return the hex code ("RRGGBB") of a [Color]
         */
        fun Color.hex(): String = "%06X".format(rgb).lowercase()

        /**
         * @return the mojang color code "§x§R§R§G§G§B§B" of a [Color]
         */
        fun Color.hexMojang(): String = buildString(14) {
            append(MC_COLOR_DELIM).append("x")
            for (c in hex()) {
                append(MC_COLOR_DELIM).append(c)
            }
        }.lowercase()

        /**
         * @return the bukkit hex code ("&x&R&R&G&G&B&B") of a [Color]
         */
        fun Color.hexBukkit(): String = buildString(14) {
            append("&x")
            for (c in hex()) {
                append("&").append(c)
            }
        }

        /**
         * @return the smallest [String] representation as either:
         * - mojang color code ("§C")
         * - mojang hex code ("§x§R§R§G§G§B§B")
         */
        fun Color.minify(): String =
            if (isExactCode) "$MC_COLOR_DELIM${colorCode.code}" // ugh
            else hexMojang()
    }

    private var _colorName: Colors? = null
    val colorName: Colors
        get() {
            _colorName?.let { return it }
            var closest = Colors.WHITE
            var distance = 0x7FFFFFFF
            for (key in Colors.entries) {
                val d = distance(key())
                if (d >= distance) continue
                distance = d
                closest = key
                if (distance == 0) {
                    isExactName = true
                    break
                }
            }
            _colorName = closest
            return closest
        }

    var isExactName = false
        private set
        get() {
            if (_colorName == null) colorName
            return field
        }


    private var _colorCode: Codes? = null
    val colorCode: Codes
        get() {
            _colorCode?.let { return it }
            var closest = Codes.WHITE
            var distance = 0x7FFFFFFF
            for (key in Codes.entries) {
                val d = distance(key())
                if (d >= distance) continue
                distance = d
                closest = key
                if (d == 0) {
                    isExactCode = true
                    break
                }
            }
            _colorCode = closest
            return closest
        }

    var isExactCode = false
        private set
        get() {
            if (_colorCode == null) colorCode
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
        Codes.entries
            .find { it.code == value.lowercaseChar() }
            ?.let {
                this.rgb = it.rgb
                this._colorCode = it
                this.isExactCode = true
            }
            ?: throw IllegalArgumentException("Invalid color code: $value")
    }

    constructor(red: Int, green: Int, blue: Int) {
        this.rgb = (red.coerceIn(0, 255) shl 16) + (green.coerceIn(0, 255) shl 8) + blue.coerceIn(0, 255)
    }

    @Throws(IllegalArgumentException::class)
    constructor(value: String) {
        try {
            val color = Colors(value)
            this.rgb = color.rgb
            this._colorName = color.colorName
            this.isExactName = true
        } catch (ex: NoSuchElementException) {
            val match = hexColorRegex.find(value) ?: throw IllegalArgumentException("Invalid hex code: $value")
            match.groupValues[1].let { hex ->
                val formatted = if (hex.length == 3) buildString {
                    append(hex[0]).append(hex[0]).append(hex[1]).append(hex[1]).append(hex[2]).append(hex[2])
                } else hex
                this.rgb = formatted.toIntOrNull(16)?.let(::Color)!!.rgb
            }
        }
    }

    private fun distance(color: Color): Int {
        val r = color.red - red
        val g = color.green - green
        val b = color.blue - blue
        return r*r + g*g + b*b
    }
}
