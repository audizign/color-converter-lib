package dev.idot.text.color

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.io.File
import kotlin.math.roundToInt

// this could be handled better
var COLORS_JSON: File = File("./colors.json")
var USE_COLORS_FROM_FILE = false
    private set
    get() = field && COLORS_JSON.exists()

const val COLOR_CHAR = '\u00a7'

val mojangRegex = Regex("&x(?:&[0-9a-f]){6}|&[0-9a-fk-or]".replace('&', COLOR_CHAR), RegexOption.IGNORE_CASE)

val mojangColorRegex = Regex("&x(?:&[0-9a-f]){6}|&[0-9a-f]".replace('&', COLOR_CHAR), RegexOption.IGNORE_CASE)

val hexCodeRegex = Regex("#?((?:[0-9a-f]{3}){1,2})", RegexOption.IGNORE_CASE)

val cmiColorRegex = Regex("\\{#((?:[0-9a-f]{3}){1,2}|[a-z_]{3,32})}", RegexOption.IGNORE_CASE)

val cmiSeparatorRegex = Regex("\\{#([0-9a-z_]{3,32})<>}", RegexOption.IGNORE_CASE)

val cmiGradientRegex = Regex("\\{#([0-9a-z_]{3,32})>}(.*?)\\{#([0-9a-z]{3,32})<}", RegexOption.IGNORE_CASE)

fun codeRegex(c: CharSequence = "&"): Regex = Regex("[$c]([0-9a-fk-or])", RegexOption.IGNORE_CASE)

fun bukkitRegex(c: CharSequence = "&"): Regex = Regex("[$c]x(?:(?:$c[0-9a-f]){3}){1,2}", RegexOption.IGNORE_CASE)

fun hexRegex(c: CharSequence = "&") = Regex("[$c]#((?:[0-9a-f]{3}){1,2})", RegexOption.IGNORE_CASE)

fun formatRegex(c: CharSequence = "&") = Regex("[$COLOR_CHAR$c][k-or]", RegexOption.IGNORE_CASE)

fun String.stripFormatCodes(): String = formatRegex().replace(this, "")

fun String.parseColorCodes(colorChar: Char = '&'): String {
    val colorString = colorChar.toString()
    return bukkitRegex(colorString).replace(this) {
        val hex = it.value
        when (hex.length) {
            8 -> {
                val result = StringBuilder(14).append(COLOR_CHAR).append("x")
                for (c in hex.drop(3).replace(colorString, "")) {
                    result.append(COLOR_CHAR).append(c).append(COLOR_CHAR).append(c)
                }
                result
            }
            14 -> hex.replace(colorChar, COLOR_CHAR)
            else -> hex
        }
    }.replace(codeRegex(colorString), "$COLOR_CHAR$1")
}

fun String.parseHexColors(colorChar: Char = '&'): String {
    return hexRegex(colorChar.toString()).replace(this) {
        val result = StringBuilder(14).append(COLOR_CHAR).append("x")
        val hex = it.groupValues[1]
        for (c in hex) {
            when (hex.length) {
                3 -> result.append(COLOR_CHAR).append(c).append(COLOR_CHAR).append(c)
                6 -> result.append(COLOR_CHAR).append(c)
                else -> return@replace it.value
            }
        }
        result
    }
}

fun String.parseCmiColors(): String {
    return cmiColorRegex.replace(this) {
        it.groupValues[1].findHexColor()?.minified() ?: it.value
    }
}

fun String.parseCmiGradient(): String {
    val separated = cmiSeparatorRegex.replace(this) { match ->
        val hexCode = match.groupValues[1].findHexColor()?.hex() ?: return@replace match.value
        val format = BooleanArray(6)
        val matches = formatRegex().findAll(this.substring(0, match.range.first)).toList()
        for (i in matches.indices.reversed()) {
            val f = matches[i].value[1].lowercaseChar()
            if (f == 'r') break
            format[f - 'k'] = true
        }

        val result = StringBuilder(22 + format.size * 2).append("{#$hexCode<}{#$hexCode>}")
        for (i in format.indices) {
            if (format[i]) result.append(COLOR_CHAR).append('k' + i)
        }
        result.toString()
    }

    return cmiGradientRegex.replace(separated) {
        val (start, text, end) = it.destructured
        try {
            text.gradient(
                start.findHexColor() ?: Colors(start),
                end.findHexColor() ?: Colors(end)
            )
        } catch(ex: Exception) {
            it.value
        }
    }
}

fun String.gradient(start: Color, end: Color, formatChars: CharSequence = "&"): String {
    if (this.isBlank()) return end.hexMojang() + this
    if (start == end) return start.hexMojang() + this
    if (this.length < 2) return start.hexMojang() + this + end.hexMojang()

    val strippedLength = this.stripFormatCodes().length
    val factor = 1.0 / (strippedLength - 1)
    val colorDiff = Triple(
        (end.red - start.red) * factor,
        (end.green - start.green) * factor,
        (end.blue - start.blue) * factor
    )

    val formatRegex = formatRegex()
    val result = StringBuilder(this.length * 14 + (this.length - strippedLength) * 2)
    var format = ""
    var textIndex = 0
    var gradientIndex = 0
    while (textIndex < this.length) {
        val char = this[textIndex]
        if (char in "$COLOR_CHAR$formatChars" && textIndex < this.length - 1) {
            val potentialFormat = this.substring(textIndex, textIndex + 2)
            if (formatRegex.matches(potentialFormat)) {
                format = if (potentialFormat[1] == 'r') "" else format + potentialFormat
                textIndex += 2
                continue
            }
        }

        if (!char.isWhitespace()) {
            val hexColor = Color(
                (start.red + colorDiff.first * gradientIndex).roundToInt(),
                (start.green + colorDiff.second * gradientIndex).roundToInt(),
                (start.blue + colorDiff.third * gradientIndex).roundToInt()
            ).minified()
            result.append(hexColor).append(format)
        }
        result.append(char)
        textIndex++
        gradientIndex++
    }
    return result.toString()
}

/* i got lazy
fun String.parseGradientColors(vararg colors: Color, formatChars: CharSequence = "&"): String {
    val sb = StringBuilder(this.length * 14)
    val split = this.stripFormatCodes().chunked(this.length / colors.size)
    for (i in 0..<colors.lastIndex) {
        sb.append(split[i].gradient(colors[i], colors[i + 1], formatChars))
    }
    return sb.toString()
}
*/

fun String.compressColors(): String {
    val matches = mojangRegex.findAll(this).toList()
    if (matches.isEmpty()) return this

    val result = StringBuilder(length).append(substring(0, matches.first().range.first))

    var lastColor = ""
    var lastFormats = BooleanArray(6)
    val sift = mutableListOf<String>()

    for (i in matches.indices) {
        val match = matches[i]
        val text = substring(match.range.last + 1..<(matches.getOrNull(i + 1)?.range?.first ?: length))

        sift.add(match.value)
        if (i != matches.lastIndex) {
            if (text.isEmpty()) continue
            if (text.isBlank()) {
                result.append(text)
                sift.clear()
                continue
            }
        }

        var newColor = ""
        val newFormats = BooleanArray(6)
        for (j in sift.indices.reversed()) {
            val c = sift[j][1]
            if (c in 'k'..'o') newFormats[c - 'k'] = true
            else {
                when (c) {
                    in '0'..'f', 'r' -> sift[j]
                    'x' -> sift[j].findMojangColor()!!.minified()
                    else -> "" // the regex makes it impossible to get here
                }.let { if (lastColor != it) newColor = it }
                break
            }
        }
        sift.clear()

        if (newColor.isNotEmpty()) {
            lastColor = newColor
            lastFormats = newFormats.copyOf()
            result.append(newColor)
            for (index in lastFormats.indices) {
                if (lastFormats[index]) result.append(COLOR_CHAR).append('k' + index)
            }
        } else {
            for (index in newFormats.indices) {
                if (newFormats[index] && !lastFormats[index]) {
                    lastFormats[index] = true
                    result.append(COLOR_CHAR).append('k' + index)
                }
            }
        }

        result.append(text)
    }
    return result.toString()
}

fun String.parseAllColors(compress: Boolean = false): String = parseCmiGradient()
    .parseCmiColors()
    .parseHexColors()
    .parseColorCodes()
    .let { if (compress) it.compressColors() else it }

fun ItemStack.parseAllColors(compress: Boolean = false): ItemStack = apply {
    itemMeta = itemMeta?.apply {
        setLocalizedName(localizedName.parseAllColors(compress))
        setDisplayName(displayName.parseAllColors(compress))
        lore = lore?.map { it.parseAllColors(compress) }
    }
}

fun ItemMeta.parseAllColors(compress: Boolean = false): ItemMeta {
    setLocalizedName(localizedName.parseAllColors(compress))
    setDisplayName(displayName.parseAllColors(compress))
    lore = lore?.map { it.parseAllColors(compress) }
    return this
}

fun String.findHexColor(): Color? {
    val found = hexCodeRegex.find(this) ?: return null
    return found.groupValues[1].let { hex ->
        val formatted = if (hex.length == 3) buildString(6) { hex.forEach { append(it).append(it) } } else hex
        formatted.toIntOrNull(16)?.let { Color(it) }
    }
}

fun String.findMojangColor(): Color? {
    if (this.length == 2) return Color(this[1])
    this.drop(3).split(COLOR_CHAR).joinToString("").let {
        return runCatching { Color(it.toInt(16)) }.getOrNull()
    }
}

@Deprecated("Inefficient, not recommended for production use.")
@Throws(IllegalArgumentException::class)
fun bruteForce(string: String): Color? {
    return (mojangColorRegex.find(string.parseCmiColors().parseHexColors().parseColorCodes())?.value ?: return null)
        .findMojangColor()
}