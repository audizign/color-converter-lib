import dev.idot.text.color.convertColors
import kotlin.test.Test
import kotlin.test.assertEquals

class Sanity {

    private fun compare(input: String, expect: String, message: String? = null) {
        assertEquals(expect, input.convertColors(true), message)
    }

    @Test
    fun colorCode0() {
        compare("&0", "§0")
        compare("&1", "§1")
        compare("&2", "§2")
        compare("&3", "§3")
        compare("&4", "§4")
        compare("&5", "§5")
        compare("&6", "§6")
        compare("&7", "§7")
        compare("&8", "§8")
        compare("&9", "§9")
        compare("&a", "§a")
        compare("&b", "§b")
        compare("&c", "§c")
        compare("&d", "§d")
        compare("&e", "§e")
        compare("&f", "§f")
        compare("&k", "§k")
        compare("&l", "§l")
        compare("&m", "§m")
        compare("&n", "§n")
        compare("&o", "§o")
        compare("&r", "§r")
        compare("&0&z", "§0&z")
    }

    @Test
    fun colorCode1() {
        compare("&0§k", "§0§k")
        compare("&0&k", "§0§k")
        compare("&0§l", "§0§l")
        compare("&0&l", "§0§l")
        compare("&0§m", "§0§m")
        compare("&0&m", "§0§m")
        compare("&0§n", "§0§n")
        compare("&0&n", "§0§n")
        compare("&0§o", "§0§o")
        compare("&0&o", "§0§o")
    }

    @Test
    fun colorCode2(){
        compare("&0   a   &0a", "§0   a   a")
        compare("&0   a  &l &0a", "§0   a   a")
    }

    @Test
    fun colorCode3() {
        compare("&0&l&m&n-&l&m&n-", "§0§l§m§n--")
        compare("&0&l&m&n-&l&m&n-&r", "§0§l§m§n--§r")
        compare("&0&l&n-&l&m&n-", "§0§l§n-§m-")
    }

    @Test
    fun bukkitHexCode0() {
        compare("&x&0&0&0", "§0")
        compare("&x&1&2&3", "§x§1§1§2§2§3§3")
        compare("&x&1&2&3&4&5", "§5")
        compare("§x§1§2§3§4§5", "§x§5")
        compare("&x&1&2&3&4&5&6", "§x§1§2§3§4§5§6")
        compare("&x&1&2&3&4&5&6&7", "§7")
        compare("&x&1&2&3 &x&4&5&6test", " §x§4§4§5§5§6§6test")
    }

    @Test
    fun ampHexCode0() {
        compare("&#000", "§0")
        compare("&#123", "§x§1§1§2§2§3§3")
        compare("&#12345", "§x§1§1§2§2§3§345")
        compare("&#123456", "§x§1§2§3§4§5§6")
        compare("&#123 &#456test", " §x§4§4§5§5§6§6test")
    }

    @Test
    fun cmiHexCode0() {
        compare("{#000}", "§0")
        compare("{#123}", "§x§1§1§2§2§3§3")
        compare("{#12345}", "{#12345}")
        compare("{#123456}", "§x§1§2§3§4§5§6")
        compare("{#123} {#456}test", " §x§4§4§5§5§6§6test")
    }

    @Test
    fun cmiGradient0() {
        compare("{#000>}{#fff<}", "§f")
        compare("{#000>}a{#fff<}", "§0a§f")
        compare("{#white>}aaaa{#fff<}", "§faaaa")
    }

    @Test
    fun cmiGradient1() {
        val expect =
            "§0H" +
            "§x§1§1§1§1§1§1e" +
            "§x§2§2§2§2§2§2&" +
            "§x§3§3§3§3§3§3a" +
            "§x§4§4§4§4§4§4l" +
            "§8l" +
            "§x§6§6§6§6§6§6o   " +
            "§7§kW" +
            "§x§b§b§b§b§b§b§ko" +
            "§x§c§c§c§c§c§c§kr" +
            "§x§d§d§d§d§d§d§kl" +
            "§x§e§e§e§e§e§e§k§ld" +
            "§f§k§l!"
        compare("{#000>}He&allo   &kWorl&ld!{#fff<}", expect)
    }

    @Test
    fun cmiGradient2() {
        val expect =
            "§00" +
            "§x§1§1§1§1§1§11" +
            "§x§2§2§2§2§2§22" +
            "§x§3§3§3§3§3§33" +
            "§x§4§4§4§4§4§44" +
            "§8&" +
            "§x§6§6§6§6§6§66   " +
            "§7a" +
            "§x§b§b§b§b§b§bb" +
            "§x§c§c§c§c§c§cc" +
            "§x§d§d§d§d§d§dd" +
            "§x§e§e§e§e§e§ee" +
            "§ff"
        compare("{#black>}01234&6   abcdef{#ffffff<}", expect)
    }

    @Test
    fun cmiGradient3() {
        val expect =
            "§x§0§0§0§0§f§fa" +
            "§x§1§7§1§7§f§fa" +
            "§x§2§e§2§e§f§f{" +
            "§x§4§6§4§6§f§f#" +
            "§x§5§d§5§d§f§fr" +
            "§x§7§4§7§4§f§fe" +
            "§x§8§b§8§b§f§fs" +
            "§x§a§2§a§2§f§f<" +
            "§x§b§9§b§9§f§f>" +
            "§x§d§1§d§1§f§f}" +
            "§x§e§8§e§8§f§fa" +
            "§fa"
        compare("{#blue>}aa{#res<>}aa{#white<}", expect)
    }

    @Test
    fun cmiGradient4() {
        val expect =
            "§0-" +
            "§x§1§8§0§0§1§8-" +
            "§x§3§1§0§0§3§1§l-" +
            "§x§4§9§0§0§4§9§l-" +
            "§x§6§1§0§0§6§1§l§m-" +
            "§x§7§9§0§0§7§9§l§m-" +
            "§x§9§2§0§0§9§2§l§m§n-" +
            "§5§l§m§n--" +
            "§x§b§6§2§4§b§6§l§m§n-" +
            "§x§c§2§4§9§c§2§l§m§n§o-" +
            "§x§c§e§6§d§c§e§l§m§n§o-" +
            "§x§d§b§9§2§d§b§k§l§m§n§o-" +
            "§x§e§7§b§6§e§7§k§l§m§n§o-" +
            "§x§f§3§d§b§f§3-" +
            "§f-"
        compare("{#black>}--&l--&m--&n--{#a0a<>}--&o--&k--&r--{#white<}", expect)
    }

    @Test
    fun optimize0() {
        compare("&x&0&0&0&0&0&0", "§0")
        compare("&x&0&0&0&0&a&a", "§1")
        compare("&x&0&0&a&a&0&0", "§2")
        compare("&x&0&0&a&a&a&a", "§3")
        compare("&x&a&a&0&0&0&0", "§4")
        compare("&x&a&a&0&0&a&a", "§5")
        compare("&x&f&f&a&a&0&0", "§6")
        compare("&x&a&a&a&a&a&a", "§7")
        compare("&x&5&5&5&5&5&5", "§8")
        compare("&x&5&5&5&5&f&f", "§9")
        compare("&x&5&5&f&f&5&5", "§a")
        compare("&x&5&5&f&f&f&f", "§b")
        compare("&x&f&f&5&5&5&5", "§c")
        compare("&x&f&f&5&5&f&f", "§d")
        compare("&x&f&f&f&f&5&5", "§e")
        compare("&x&f&f&f&f&f&f", "§f")
    }

    @Test
    fun optimize1() {
        compare("&0&1", "§1")
        compare("&0text&1text&0text&1", "§0text§1text§0text§1")
        compare("&0&1&2&3&4&5&6&7&8&9&a&b&c&d&e&f&k&l&m&n&o&r", "§r")
        compare("&0&1&2&3&4&5&6&7&8&9&a&b&c&d&e&f&k&l&m&n&o&rtext", "§rtext")
    }

    @Test
    fun optimize2() {
        val expect = "§0texttext"
        compare("&0text&0text", expect)
        compare("&0text&l&0text", expect)
        compare("&0text&0&ltext", "§0text§ltext")
    }

    @Test
    fun optimize3() {
        val expect = "§1text"
        compare("&0&1text", expect)
        compare("&0&l&1text", expect)

        compare("&0&1&ltext", "§1§ltext")
    }

}