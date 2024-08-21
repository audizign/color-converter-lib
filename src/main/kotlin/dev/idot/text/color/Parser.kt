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

fun codeRegex(c: Char = '&'): Regex = Regex("$c([0-9a-fk-or])", RegexOption.IGNORE_CASE)

fun bukkitRegex(c: Char = '&'): Regex = Regex(c + "x(?:(?:$c[0-9a-f]){3}){1,2}", RegexOption.IGNORE_CASE)

fun hexRegex(c: Char = '&') = Regex("$c#((?:[0-9a-f]{3}){1,2})", RegexOption.IGNORE_CASE)

fun formatRegex(c: Char = '&') = Regex("[$COLOR_CHAR$c][k-or]", RegexOption.IGNORE_CASE)

fun String.stripFormatCodes(): String = formatRegex().replace(this, "")

fun String.convertColorCodes(colorChar: Char = '&'): String = bukkitRegex(colorChar).replace(this) {
    val hex = it.value
    when (hex.length) {
        8 -> {
            val result = StringBuilder(14).append(COLOR_CHAR).append("x")
            for (c in hex.drop(3).replace(colorChar.toString(), "")) {
                result.append(COLOR_CHAR).append(c).append(COLOR_CHAR).append(c)
            }
            result
        }
        14 -> hex.replace(colorChar, COLOR_CHAR)
        else -> hex
    }
}.replace(codeRegex(colorChar), "$COLOR_CHAR$1")

fun String.convertHexColors(colorChar: Char = '&'): String = hexRegex(colorChar).replace(this) {
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

fun String.convertCmiColors(): String = cmiColorRegex.replace(this) {
    it.groupValues[1].findHexColor()?.minify() ?: it.value
}

fun String.convertCmiGradient(): String {
    val separated = cmiSeparatorRegex.replace(this) { match ->
        val hexCode = match.groupValues[1].findHexColor()?.hex() ?: return@replace match.value
        val format = BooleanArray(6)
        val matches = formatRegex().findAll(substring(0, match.range.first)).toList()
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
    if (isBlank()) return end.hexMojang() + this
    if (start == end) return start.hexMojang() + this
    if (length < 2) return start.hexMojang() + this + end.hexMojang()

    val strippedLength = this.stripFormatCodes().length
    val factor = 1.0 / (strippedLength - 1)
    val redDiff = (end.red - start.red) * factor
    val greenDiff = (end.green - start.green) * factor
    val blueDiff = (end.blue - start.blue) * factor

    val formatRegex = formatRegex()
    val result = StringBuilder(length * 14 + (length - strippedLength) * 2)
    var format = ""
    var textIndex = 0
    var gradientIndex = 0
    while (textIndex < length) {
        val char = this[textIndex]
        if (char in "$COLOR_CHAR$formatChars" && textIndex < length - 1) {
            val potentialFormat = substring(textIndex, textIndex + 2)
            if (formatRegex.matches(potentialFormat)) {
                format = if (potentialFormat[1] == 'r') "" else format + potentialFormat
                textIndex += 2
                continue
            }
        }

        if (!char.isWhitespace()) {
            val hexColor = Color(
                (start.red + redDiff * gradientIndex).roundToInt(),
                (start.green + greenDiff * gradientIndex).roundToInt(),
                (start.blue + blueDiff * gradientIndex).roundToInt()
            ).minify()
            result.append(hexColor).append(format)
        }
        result.append(char)
        textIndex++
        gradientIndex++
    }
    return result.toString()
}

/* i got lazy
fun String.convertGradientColors(vararg colors: Color, formatChars: CharSequence = "&"): String {
    val sb = StringBuilder(this.length * 14)
    val split = this.stripFormatCodes().chunked(this.length / colors.size)
    for (i in 0..<colors.lastIndex) {
        sb.append(split[i].gradient(colors[i], colors[i + 1], formatChars))
    }
    return sb.toString()
}
*/

fun String.minifyColors(): String {
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
        for (s in sift.indices.reversed()) {
            val c = sift[s][1].lowercaseChar()
            if (c in 'k'..'o') {
                newFormats[c - 'k'] = true
                continue
            }
            when (c) { // ew why does this have to be so ugly
                in "0123456789abcdefr" -> sift[s]
                'x' -> sift[s].findMojangColor()!!.minify()
                else -> "" // the regex makes it impossible to get here
            }.let { if (lastColor != it) newColor = it }
            break
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



fun String.convertAllColors(): String = convertCmiGradient()
    .convertCmiColors()
    .convertHexColors()
    .convertColorCodes()

fun String.convertAllColors(minify: Boolean): String =
    convertAllColors().let { if (minify) it.minifyColors() else it }

fun ItemStack.convertAllColors(): ItemStack = apply {
    itemMeta?.convertAllColors()?.let { itemMeta = it }
}

fun ItemStack.convertAllColors(minify: Boolean = false): ItemStack = apply {
    itemMeta?.convertAllColors(minify)?.let { itemMeta = it }
}

fun ItemMeta.convertAllColors(): ItemMeta = apply {
    setLocalizedName(localizedName.convertAllColors())
    setDisplayName(displayName.convertAllColors())
    lore = lore?.map { it.convertAllColors() }
}

fun ItemMeta.convertAllColors(minify: Boolean = false): ItemMeta = apply {
    setLocalizedName(localizedName.convertAllColors(minify))
    setDisplayName(displayName.convertAllColors(minify))
    lore = lore?.map { it.convertAllColors(minify) }
}

fun String.findHexColor(): Color? {
    return (hexCodeRegex.find(this) ?: return null).groupValues[1].let { hex ->
        val formatted = if (hex.length == 3) buildString {
            append(hex[0]).append(hex[0])
            append(hex[1]).append(hex[1])
            append(hex[2]).append(hex[2])
        } else hex
        formatted.toIntOrNull(16)?.let(::Color)
    }
}



fun String.findMojangColor(): Color? {
    if (this.length == 2) return Color(this[1])
    drop(3).split(COLOR_CHAR).joinToString("").let {
        return runCatching { Color(it.toInt(16)) }.getOrNull()
    }
}

@Deprecated("Inefficient, not recommended for production use.")
@Throws(IllegalArgumentException::class)
fun bruteForce(string: String): Color? {
    return (mojangColorRegex.find(string.convertCmiColors().convertHexColors().convertColorCodes())?.value ?: return null)
        .findMojangColor()
}