package dev.idot.text.color

import dev.idot.text.color.Color.Companion.colorFromHex
import dev.idot.text.color.Color.Companion.findMojangColor
import dev.idot.text.color.Color.Companion.hex
import dev.idot.text.color.Color.Companion.hexMojang
import dev.idot.text.color.Color.Companion.minify
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.io.File
import kotlin.math.roundToInt
import kotlin.text.RegexOption.IGNORE_CASE

/**
 * So, I'm bad at naming things. But here's a quick rundown:
 *
 * "Codes" ("Color Codes" or "Hex Codes") are minecraft-specific coded colors
 *
 * "Colors" ("Hex Colors") are standard hex colors, named [Colors], or [Color] objects
 *
 */

// this could be handled better
var COLORS_JSON: File? = File("./colors.json")
var USE_COLORS_FROM_FILE = false
    private set
    get() = field && (COLORS_JSON?.exists() ?: false)

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
 * @param delimiter for color code (default: [COLOR_DELIM])
 * @return the [Regex] for color codes ("&C")
 */
fun colorCodeRegex(delimiter: Char = COLOR_DELIM) =
    Regex("[$MC_COLOR_DELIM$delimiter]([0-9a-f])", IGNORE_CASE)

/**
 * @param delimiter for format code (default: [COLOR_DELIM])
 * @return the [Regex] for format codes ("&k-o" and "&r")
 */
fun formatCodeRegex(delimiter: Char = COLOR_DELIM) =
    Regex("[$MC_COLOR_DELIM$delimiter]([k-or])", IGNORE_CASE)

/**
 * @param delimiter for color code (default: [COLOR_DELIM])
 * @return the [Regex] for bukkit hex codes ("&x&R&R&G&G&B&B" and "&x&R&G&B")
 */
fun bukkitHexRegex(delimiter: Char = COLOR_DELIM) =
    Regex("&x(?:(?:&[0-9a-f]){3}){1,2}".replace('&', delimiter), IGNORE_CASE)

/**
 * @param delimiter for color code (default: [COLOR_DELIM])
 * @return the [Regex] for hex codes ("&amp;#RRGGBB" and "&amp;#RGB")
 */
fun hexCodeRegex(delimiter: Char = COLOR_DELIM) =
    Regex("$delimiter#((?:[0-9a-f]{3}){1,2})", IGNORE_CASE)

/**
 * Does not remove format codes; see [stripFormatting]
 *
 * Does not remove gradient codes; see String#convertCmiGradient() then perform [stripFormatting]
 *
 * @param delimiter the for color code
 * @return the [String] without any color codes ("&C") and hex codes ("&#RRGGBB" and "&#RGB")
 */
fun String.stripColoring(delimiter: Char = COLOR_DELIM): String =
    replace(cmiColorRegex, "")
        .replace(hexCodeRegex(delimiter), "")
        .replace(bukkitHexRegex(delimiter), "")
        .replace(colorCodeRegex(delimiter), "")
        .replace(mojangRegex, "")


fun Collection<String>.stripColoring() = map { it.stripColoring() }

/**
 * @param delimiter for color code (default: [COLOR_DELIM])
 * @return the string without any format codes ("&k-o" and "&r")
 */
fun String.stripFormatting(delimiter: Char = COLOR_DELIM): String = formatCodeRegex(delimiter).replace(this, "")

fun Collection<String>.stripFormatting() = map { it.stripFormatting() }

/**
 * @param delimiter for color code (default: [COLOR_DELIM])
 * @return the [String] with all color codes ("&C") converted to mojang color codes ("§C")
 */
fun String.convertColorCodes(delimiter: Char = COLOR_DELIM): String = replace(bukkitHexRegex(delimiter)) { match ->
    val hex = match.value
    when (hex.length) {
        8 -> StringBuilder(14).append(MC_COLOR_DELIM).append("x").apply {
            for (c in hex.drop(3)) {
                if (c == delimiter) continue
                append(MC_COLOR_DELIM).append(c).append(MC_COLOR_DELIM).append(c)
            }
        }

        14 -> hex.replace(delimiter, MC_COLOR_DELIM)
        else -> hex
    }
}.replace(colorCodeRegex(delimiter), "$MC_COLOR_DELIM$1").replace(formatCodeRegex(delimiter), "$MC_COLOR_DELIM$1")

fun Collection<String>.convertColorCodes() = map { it.convertColorCodes() }

/**
 * @param delimiter for color code (default: [COLOR_DELIM])
 * @return the [String] with all hex codes ("&amp;#RRGGBB" and "&amp;#RGB") converted
 * to mojang hex codes ("§x§R§R§G§G§B§B")
 */
fun String.convertHexCodes(delimiter: Char = COLOR_DELIM): String = replace(hexCodeRegex(delimiter)) {
    val hex = it.groupValues[1]
    when (hex.length) {
        3 -> StringBuilder(14).apply {
            append(MC_COLOR_DELIM).append('x')
            for (c in hex) {
                append(MC_COLOR_DELIM).append(c).append(MC_COLOR_DELIM).append(c)
            }
        }

        6 -> StringBuilder(14).apply {
            append(MC_COLOR_DELIM).append('x')
            for (c in hex) {
                append(MC_COLOR_DELIM).append(c)
            }
        }

        else -> it.value
    }
}

fun Collection<String>.convertHexCodes() = map { it.convertHexCodes() }

/**
 * @return the [String] with all cmi color codes ("{#RRGGBB}", "{#RGB}" or "{#COLORNAME}") converted
 * to minified mojang color codes ("&C" or "§x§R§R§G§G§B§B")
 */
fun String.convertCmiColors(): String =
    replace(cmiColorRegex) { it.groupValues[1].colorFromHex()?.minify() ?: it.value }

fun Collection<String>.convertCmiColors() = map { it.convertCmiColors() }

/**
 * @return the [String] with all cmi gradient codes ("{#color1>}{#color2<>}{#color3<}" etc.) converted
 * to mojang color codes ("§x§R§R§G§G§B§B")
 */
fun String.convertCmiGradient(): String = replace(cmiSeparatorRegex) { match ->
    val hexCode = match.groupValues[1].colorFromHex()?.hex() ?: return@replace match.value
    val format = BooleanArray(6)
    val matches = formatCodeRegex().findAll(substring(0, match.range.first)).toList()
    for (i in matches.indices.reversed()) { // reversed for proper sorting
        val f = matches[i].value[1].lowercaseChar()
        if (f == 'r') break
        format[f - 'k'] = true
    }

    StringBuilder(22 + format.size * 2).append("{#$hexCode<}{#$hexCode>}").apply {
        for (i in format.indices) {
            if (format[i]) append(MC_COLOR_DELIM).append('k' + i)
        }
    }
}.replace(cmiGradientRegex) { result ->
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

/**
 * @param start the starting [Color]
 * @param end the ending [Color]
 * @param formatChar for color code (default: [COLOR_DELIM])
 * @return the [String] with a gradient from [start] to [end]
 */
fun String.gradient(start: Color, end: Color, formatChar: Char = COLOR_DELIM): String {
    if (isBlank()) return end.hexMojang() + this
    if (start == end) return start.hexMojang() + this
    if (length < 2) return start.hexMojang() + this + end.hexMojang()

    val strippedLength = stripFormatting().lastIndex
    val factor = 1.0 / (strippedLength)
    val redDiff = (end.red - start.red) * factor
    val greenDiff = (end.green - start.green) * factor
    val blueDiff = (end.blue - start.blue) * factor

    val formatRegex = formatCodeRegex()
    val result = StringBuilder(length * 14 + (length - strippedLength + 1) * 2)
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
 * @param delimiter for color code (default: [COLOR_DELIM])
 * @return the [String] with all possible color codes converted to mojang color codes ("§C", "§x§R§R§G§G§B§B")
 */
fun String.convertColors(delimiter: Char = COLOR_DELIM) = convertCmiGradient()
    .convertCmiColors()
    .convertHexCodes(delimiter)
    .convertColorCodes(delimiter)

/**
 * @param minify see [minifyColors]
 * @return the string with all possible color codes converted to mojang color codes. (Includes gradients)
 */
fun String.convertColors(minify: Boolean) =
    convertColors().let { if (minify) it.minifyColors() else it }

fun Collection<String>.convertColors() = map { it.convertColors() }

fun Collection<String>.convertColors(minify: Boolean) = map { it.convertColors(minify) }

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