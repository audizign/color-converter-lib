package dev.idot.text.color

enum class Codes(val hex: String, val rgb: Int, val code: Char) {
    BLACK("#000000", 0x000000, '0'),
    DARK_BLUE("#0000AA", 0x0000AA, '1'),
    DARK_GREEN("#00AA00", 0x00AA00, '2'),
    DARK_AQUA("#00AAAA", 0x00AAAA, '3'),
    DARK_RED("#AA0000", 0xAA0000, '4'),
    DARK_PURPLE("#AA00AA", 0xAA00AA, '5'),
    GOLD("#FFAA00", 0xFFAA00, '6'),
    GRAY("#AAAAAA", 0xAAAAAA, '7'),
    DARK_GRAY("#555555", 0x555555, '8'),
    BLUE("#5555FF", 0x5555FF, '9'),
    GREEN("#55FF55", 0x55FF55, 'a'),
    AQUA("#55FFFF", 0x55FFFF, 'b'),
    RED("#FF5555", 0xFF5555, 'c'),
    LIGHT_PURPLE("#FF55FF", 0xFF55FF, 'd'),
    YELLOW("#FFFF55", 0xFFFF55, 'e'),
    WHITE("#FFFFFF", 0xFFFFFF, 'f');

    operator fun invoke() = Color(rgb)
}