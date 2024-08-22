package dev.idot.text.color

import dev.idot.text.color.Color.Companion.colorFromHex
import dev.idot.text.color.Color.Companion.findMojangColor
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import kotlin.text.RegexOption.IGNORE_CASE
import java.io.File
import kotlin.math.roundToInt

/**
 * "Color codes" or "hex codes" are minecraft-specific coded colors
 * "Hex Colors" are standard hex color formats or named colors
 * This is already confusing, I know :(
 */

// this could be handled better
var COLORS_JSON: File = File("./colors.json")
var USE_COLORS_FROM_FILE = false
    private set
    get() = field && COLORS_JSON.exists()

const val COLOR_DELIM = '\u00a7'
var GLOBAL_COLOR_DELIM = '&'

val mojangRegex = Regex("&x(?:&[0-9a-f]){6}|&[0-9a-fk-or]".replace('&', COLOR_DELIM), IGNORE_CASE)

val mojangCodeRegex = Regex("&x(?:&[0-9a-f]){6}|&[0-9a-f]".replace('&', COLOR_DELIM), IGNORE_CASE)

val hexColorRegex = Regex("#?((?:[0-9a-f]{3}){1,2})", IGNORE_CASE)

val cmiColorRegex = Regex("\\{#((?:[0-9a-f]{3}){1,2}|[a-z_]{3,32})}", IGNORE_CASE)

val cmiSeparatorRegex = Regex("\\{#([0-9a-z_]{3,32})<>}", IGNORE_CASE)

val cmiGradientRegex = Regex("\\{#([0-9a-z_]{3,32})>}(.*?)\\{#([0-9a-z]{3,32})<}", IGNORE_CASE)

/**
 * @param colorChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the [Regex] for bukkit color codes ("&0")
 */
fun bukkitCodeRegex(colorChar: Char = GLOBAL_COLOR_DELIM) =
    Regex("$colorChar([0-9a-fk-or])", IGNORE_CASE)

/**
 * @param colorChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the [Regex] for bukkit hex codes ("&x&0&0&0&0&0&0" and "&x&0&0&0")
 */
fun bukkitHexRegex(colorChar: Char = GLOBAL_COLOR_DELIM) =
    Regex(colorChar + "x(?:(?:$colorChar[0-9a-f]){3}){1,2}", IGNORE_CASE)

/**
 * @param colorChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the [Regex] for hex codes ("&amp;#000000" and "&amp;#000")
 */
fun hexCodeRegex(colorChar: Char = GLOBAL_COLOR_DELIM) =
    Regex("$colorChar#((?:[0-9a-f]{3}){1,2})", IGNORE_CASE)

/**
 * @param colorChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the [Regex] for format codes ("&k-o" and "&r")
 */
fun formatRegex(colorChar: Char = GLOBAL_COLOR_DELIM) =
    Regex("[$COLOR_DELIM$colorChar][k-or]", IGNORE_CASE)

/**
 * Does not remove format codes; see [stripFormatCodes]
 * Does not remove gradient codes; see String#convertCmiGradient() then perform [stripColorCodes]
 *
 * @param colorChar the color code delimiter
 * @return the [String] without any color codes ("&0") and hex codes ("&#000000" and "&#000")
 */
fun String.stripColorCodes(colorChar: Char = GLOBAL_COLOR_DELIM): String = apply {
    replace(cmiColorRegex, "")
    replace(hexCodeRegex(colorChar), "")
    replace(bukkitHexRegex(colorChar), "")
    replace(bukkitCodeRegex(colorChar), "")
    replace(mojangRegex, "")
}

/**
 * @param colorChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the string without any format codes ("&k-o" and "&r")
 */
fun String.stripFormatCodes(colorChar: Char = GLOBAL_COLOR_DELIM) = formatRegex(colorChar).replace(this, "")

/**
 * @param colorChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the [String] with all color codes ("&0") converted to mojang color codes ("§0")
 */
fun String.convertColorCodes(colorChar: Char = GLOBAL_COLOR_DELIM) = bukkitHexRegex(colorChar).replace(this) {
    val hex = it.value
    when (hex.length) {
        8 -> {
            val result = StringBuilder(14).append(COLOR_DELIM).append("x")
            for (c in hex.drop(3).replace(colorChar.toString(), "")) {
                result.append(COLOR_DELIM).append(c).append(COLOR_DELIM).append(c)
            }
            result
        }

        14 -> hex.replace(colorChar, COLOR_DELIM)
        else -> hex
    }
}.replace(bukkitCodeRegex(colorChar), "$COLOR_DELIM$1")

/**
 * @param colorChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the [String] with all hex codes ("&amp;#000000" and "&amp;#000") converted
 * to mojang hex codes ("§x§0§0§0§0§0§0")
 */
fun String.convertHexColors(colorChar: Char = GLOBAL_COLOR_DELIM): String = hexCodeRegex(colorChar).replace(this) {
    val result = StringBuilder(14).append(COLOR_DELIM).append("x")
    val hex = it.groupValues[1]
    for (c in hex) {
        when (hex.length) {
            3 -> result.append(COLOR_DELIM).append(c).append(COLOR_DELIM).append(c)
            6 -> result.append(COLOR_DELIM).append(c)
            else -> return@replace it.value
        }
    }
    result
}

/**
 * @return the [String] with all cmi color codes ("{#000000}", "{#000}" or "{#black}") converted
 * to mojang color codes ("§x§0§0§0§0§0§0")
 */
fun String.convertCmiColors(): String = cmiColorRegex.replace(this) {
    it.groupValues[1].colorFromHex()?.minify() ?: it.value
}

/**
 * @return the [String] with all cmi gradient codes ("{#red>}{#green<>}{#blue<}" and etc.) converted
 * to mojang color codes ("§x§0§0§0§0§0§0")
 */
fun String.convertCmiGradient(): String {
    val separated = cmiSeparatorRegex.replace(this) { match ->
        val hexCode = match.groupValues[1].colorFromHex()?.hex() ?: return@replace match.value
        val format = BooleanArray(6)
        val matches = formatRegex().findAll(substring(0, match.range.first)).toList()
        for (i in matches.indices.reversed()) {
            val f = matches[i].value[1].lowercaseChar()
            if (f == 'r') break
            format[f - 'k'] = true
        }

        val result = StringBuilder(22 + format.size * 2).append("{#$hexCode<}{#$hexCode>}")
        for (i in format.indices) {
            if (format[i]) result.append(COLOR_DELIM).append('k' + i)
        }
        result.toString()
    }
    return cmiGradientRegex.replace(separated) { result ->
        val (start, text, end) = result.destructured
        try {
            text.gradient(
                start.colorFromHex() ?: Colors(start),
                end.colorFromHex() ?: Colors(end)
            )
        } catch (ex: Exception) {
            result.value
        }
    }
}

/**
 * @param start
 * @param end
 * @param formatChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the [String] with a gradient from [start] to [end]
 */
fun String.gradient(start: Color, end: Color, formatChar: Char = GLOBAL_COLOR_DELIM): String {
    if (isBlank()) return end.hexMojang() + this
    if (start == end) return start.hexMojang() + this
    if (length < 2) return start.hexMojang() + this + end.hexMojang()

    val strippedLength = stripFormatCodes().length
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
        if ((char == COLOR_DELIM || char == formatChar) && textIndex < length - 1) {
            val potentialFormat = substring(textIndex, textIndex + 2)
            if (formatRegex.matches(potentialFormat)) {
                format = if (potentialFormat[1] == 'r') "" else format + potentialFormat
                textIndex += 2
                continue
            }
        }

        if (!char.isWhitespace()) {
            Color(
                (start.red + redDiff * gradientIndex).roundToInt(),
                (start.green + greenDiff * gradientIndex).roundToInt(),
                (start.blue + blueDiff * gradientIndex).roundToInt()
            ).minify().also {
                result.append(it).append(format)
            }
        }
        result.append(char)
        textIndex++
        gradientIndex++
    }
    return result.toString()
}

/* i got lazy and didn't want to implement this yet
fun String.convertGradientColors(vararg colors: Color, formatChars: CharSequence = "&"): String {
    val sb = StringBuilder(this.length * 14)
    val split = this.stripFormatCodes().chunked(this.length / colors.size)
    for (i in 0..<colors.lastIndex) {
        sb.append(split[i].gradient(colors[i], colors[i + 1], formatChars))
    }
    return sb.toString()
}
*/

/**
 *
 * @see String[convertCmiGradient] then perform [stripColorCodes] as this does not replace gradient codes
 * @return the string with all unnecessary color and formatting codes removed
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
                if (lastFormats[index]) result.append(COLOR_DELIM).append('k' + index)
            }
        } else {
            for (index in newFormats.indices) {
                if (newFormats[index] && !lastFormats[index]) {
                    lastFormats[index] = true
                    result.append(COLOR_DELIM).append('k' + index)
                }
            }
        }

        result.append(text)
    }
    return result.toString()
}

/**
 * @param colorChar color code delimiter (default: [GLOBAL_COLOR_DELIM])
 * @return the [String] with all possible color codes converted to mojang color codes ("§0", "§x§0§0§0§0§0§0")
 */
fun String.convertColors(colorChar: Char = GLOBAL_COLOR_DELIM) = convertCmiGradient()
    .convertCmiColors()
    .convertHexColors(colorChar)
    .convertColorCodes(colorChar)

/**
 * @param minify see [minifyColors]
 * @return the string with all possible color codes converted to mojang color codes. (Includes gradients)
 */
fun String.convertColors(minify: Boolean) =
    convertColors().let { if (minify) it.minifyColors() else it }

/**
 * @return [ItemStack] see String#[convertColors]
 */
fun ItemStack.convertColors() = apply {
    itemMeta?.convertColors()?.let { itemMeta = it }
}

/**
 * @param minify see [minifyColors]
 * @return [ItemStack] see String#[convertColors]
 */
fun ItemStack.convertColors(minify: Boolean = false) = apply {
    itemMeta?.convertColors(minify)?.let { itemMeta = it }
}

/**
 * @return see String#[convertColors]
 */
fun ItemMeta.convertColors() = apply {
    setLocalizedName(localizedName.convertColors())
    setDisplayName(displayName.convertColors())
    lore = lore?.map { it.convertColors() }
}

/**
 * @param minify see [minifyColors]
 * @return see String#[convertColors]
 */
fun ItemMeta.convertColors(minify: Boolean = false) = apply {
    setLocalizedName(localizedName.convertColors(minify))
    setDisplayName(displayName.convertColors(minify))
    lore = lore?.map { it.convertColors(minify) }
}