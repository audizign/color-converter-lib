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
 *
 * "Hex Colors" are standard hex color formats or named colors
 *
 * This is already confusing, I know :(
 */

// this could be handled better
var COLORS_JSON: File = File("./colors.json")
var USE_COLORS_FROM_FILE = false
    private set
    get() = field && COLORS_JSON.exists()

const val MC_COLOR_DELIM = '\u00a7'

/**
 * @return the custom delimiter for color codes in this class (default: '&')
 */
var COLOR_DELIM: Char = '&'

val mojangRegex = Regex("&x(?:&[0-9a-f]){6}|&[0-9a-fk-or]".replace('&', MC_COLOR_DELIM), IGNORE_CASE)

val mojangCodeRegex = Regex("&x(?:&[0-9a-f]){6}|&[0-9a-f]".replace('&', MC_COLOR_DELIM), IGNORE_CASE)

val hexColorRegex = Regex("#?((?:[0-9a-f]{3}){1,2})", IGNORE_CASE)

val cmiColorRegex = Regex("\\{#((?:[0-9a-f]{3}){1,2}|[a-z_]{3,32})}", IGNORE_CASE)

val cmiSeparatorRegex = Regex("\\{#([0-9a-z_]{3,32})<>}", IGNORE_CASE)

val cmiGradientRegex = Regex("\\{#([0-9a-z_]{3,32})>}(.*?)\\{#([0-9a-z]{3,32})<}", IGNORE_CASE)

/**
 * @param colorChar color code delimiter (default: [COLOR_DELIM])
 * @return the [Regex] for bukkit color codes ("&C")
 */
fun bukkitCodeRegex(colorChar: Char = COLOR_DELIM) =
    Regex("$colorChar([0-9a-fk-or])", IGNORE_CASE)

/**
 * @param colorChar color code delimiter (default: [COLOR_DELIM])
 * @return the [Regex] for bukkit hex codes ("&x&R&R&G&G&B&B" and "&x&R&G&B")
 */
fun bukkitHexRegex(colorChar: Char = COLOR_DELIM) =
    Regex("&x(?:(?:&[0-9a-f]){3}){1,2}".replace('&', colorChar), IGNORE_CASE)

/**
 * @param colorChar color code delimiter (default: [COLOR_DELIM])
 * @return the [Regex] for hex codes ("&amp;#RRGGBB" and "&amp;#RGB")
 */
fun hexCodeRegex(colorChar: Char = COLOR_DELIM) =
    Regex("$colorChar#((?:[0-9a-f]{3}){1,2})", IGNORE_CASE)

/**
 * @param colorChar color code delimiter (default: [COLOR_DELIM])
 * @return the [Regex] for format codes ("&k-o" and "&r")
 */
fun formatRegex(colorChar: Char = COLOR_DELIM) =
    Regex("[$MC_COLOR_DELIM$colorChar][k-or]", IGNORE_CASE)

/**
 * Does not remove format codes; see [stripFormatCodes]
 *
 * Does not remove gradient codes; see String#convertCmiGradient() then perform [stripColorCodes]
 *
 * @param colorChar the color code delimiter
 * @return the [String] without any color codes ("&C") and hex codes ("&#RRGGBB" and "&#RGB")
 */
fun String.stripColorCodes(colorChar: Char = COLOR_DELIM): String = apply {
    replace(cmiColorRegex, "")
    replace(hexCodeRegex(colorChar), "")
    replace(bukkitHexRegex(colorChar), "")
    replace(bukkitCodeRegex(colorChar), "")
    replace(mojangRegex, "")
}

/**
 * @param colorChar color code delimiter (default: [COLOR_DELIM])
 * @return the string without any format codes ("&k-o" and "&r")
 */
fun String.stripFormatCodes(colorChar: Char = COLOR_DELIM) = formatRegex(colorChar).replace(this, "")

/**
 * @param colorChar color code delimiter (default: [COLOR_DELIM])
 * @return the [String] with all color codes ("&C") converted to mojang color codes ("§C")
 */
fun String.convertColorCodes(colorChar: Char = COLOR_DELIM) = bukkitHexRegex(colorChar).replace(this) {
    val hex = it.value
    when (hex.length) {
        8 -> {
            val result = StringBuilder(14).append(MC_COLOR_DELIM).append("x")
            for (c in hex.drop(3).replace(colorChar.toString(), "")) {
                result.append(MC_COLOR_DELIM).append(c).append(MC_COLOR_DELIM).append(c)
            }
            result
        }

        14 -> hex.replace(colorChar, MC_COLOR_DELIM)
        else -> hex
    }
}.replace(bukkitCodeRegex(colorChar), "$MC_COLOR_DELIM$1")

/**
 * @param colorChar color code delimiter (default: [COLOR_DELIM])
 * @return the [String] with all hex codes ("&amp;#RRGGBB" and "&amp;#RGB") converted
 * to mojang hex codes ("§x§R§R§G§G§B§B")
 */
fun String.convertHexColors(colorChar: Char = COLOR_DELIM): String = hexCodeRegex(colorChar).replace(this) {
    val result = StringBuilder(14).append(MC_COLOR_DELIM).append("x")
    val hex = it.groupValues[1]
    for (c in hex) {
        when (hex.length) {
            3 -> result.append(MC_COLOR_DELIM).append(c).append(MC_COLOR_DELIM).append(c)
            6 -> result.append(MC_COLOR_DELIM).append(c)
            else -> return@replace it.value
        }
    }
    result
}

/**
 * @return the [String] with all cmi color codes ("{#RRGGBB}", "{#RGB}" or "{#COLORNAME}") converted
 * to mojang color codes ("§x§R§R§G§G§B§B")
 */
fun String.convertCmiColors(): String = cmiColorRegex.replace(this) {
    it.groupValues[1].colorFromHex()?.minify() ?: it.value
}

/**
 * @return the [String] with all cmi gradient codes ("{#color1>}{#color2<>}{#color3<}" etc.) converted
 * to mojang color codes ("§x§R§R§G§G§B§B")
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
            if (format[i]) result.append(MC_COLOR_DELIM).append('k' + i)
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
 * @param start the starting [Color]
 * @param end the ending [Color]
 * @param formatChar color code delimiter (default: [COLOR_DELIM])
 * @return the [String] with a gradient from [start] to [end]
 */
fun String.gradient(start: Color, end: Color, formatChar: Char = COLOR_DELIM): String {
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
        if ((char == MC_COLOR_DELIM || char == formatChar) && textIndex < length - 1) {
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
 * As this does not replace cmi gradient codes, use String[convertCmiGradient] first
 * @return the following [String]:
 *
 * - shortens color codes, if possible ("§x§a§a§0§0§a§a" -> "§5")
 *
 * - removes unnecessary mojang color codes ("§C" and "§x§R§R§G§G§B§B")
 *
 * - removes unnecessary mojang format codes ("§F") and sorts leading codes alphabetically
 */
fun String.minifyColors(): String {
    val matches = mojangRegex.findAll(this).toList()
    if (matches.isEmpty()) return this

    val result = StringBuilder(length).append(substring(0, matches.first().range.first))

    var lastColor = ""
    var lastFormats = BooleanArray(5)
    val sift = mutableListOf<String>()

    for (i in matches.indices) {
        val match = matches[i]
        val text = substring(match.range.last + 1..<(matches.getOrNull(i + 1)?.range?.first ?: length))

        sift.add(match.value)

        // whitespace handling
        if (i != matches.lastIndex) {
            if (text.isEmpty()) continue
            if (text.isBlank()) {
                result.append(text)
                sift.clear()
                continue
            }
        }

        var newColor = ""
        val newFormats = BooleanArray(5) // me trying to optimize lol
        for (s in sift.indices.reversed()) { // reversing the sift allows alphabetical sorting
            val c = sift[s][1].lowercaseChar()
            if (c in 'k'..'o') {
                newFormats[c - 'k'] = true
                continue // this is so important
            } else if (c in "0123456789abcdefr") // r is considered a color in this case
                sift[s].let { if (lastColor != it) newColor = it }
            else if (c == 'x')
                sift[s].findMojangColor()!!.minify().let { if (lastColor != it) newColor = it }
            break
        }
        sift.clear()

        if (newColor.isEmpty()) {
            for (index in newFormats.indices) {
                if (newFormats[index] && !lastFormats[index]) {
                    lastFormats[index] = true
                    result.append(MC_COLOR_DELIM).append('k' + index)
                }
            }
        } else {
            lastColor = newColor
            lastFormats = newFormats.copyOf()
            result.append(newColor)
            for (index in lastFormats.indices) {
                if (lastFormats[index]) result.append(MC_COLOR_DELIM).append('k' + index)
            }
        }

        result.append(text)
    }
    return result.toString()
}

/**
 * @param colorChar color code delimiter (default: [COLOR_DELIM])
 * @return the [String] with all possible color codes converted to mojang color codes ("§C", "§x§R§R§G§G§B§B")
 */
fun String.convertColors(colorChar: Char = COLOR_DELIM) = convertCmiGradient()
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