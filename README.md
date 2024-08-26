# color-converter-lib

converting minecraft color strings more intuitive... for me

please don't use this on production; am refactoring (breaking) it often

but feel free to fork it and use it however you like in your code (why not); see the license

## examples
String#convertColors().minifyColors()
```
&cHello &aworld! -> §cHello §aworld!

&#ff0000Hello &#00ff00world! -> §cHello §aworld!

{#ff0000}Hello {#0f0}world! -> §cHello §aworld!

{#black>}0123456   abcdef{#ffffff<} -> "§00§x§1§1§1§1§1§11§x§2§2§2§2§2§22§x§3§3§3§3§3§33§x§4§4§4§4§4§44§85§x§6§6§6§6§6§66   §7a§x§b§b§b§b§b§bb§x§c§c§c§c§c§cc§x§d§d§d§d§d§dd§x§e§e§e§e§e§ee§ff
```

```
val color1 = Colors.DOUBLECOLONIALWHITE()
val color2 = Color("hummingbird")
val line1 = "&o        ".gradient(color1, color2)
val line2 = "&o        ".gradient(color2, color1)

fun wavyLine() = buildString {
    repeat(4) {
        append(line1)
        append(line2)
    }
    append(line1)
}.convertColors(minify = true)
```

# usage

### 1. clone the repo
```
https://github.com/audizign/color-converter-lib.git
```

### 2. publish to maven

local maven repo:

- Gradle -> Tasks -> publishToMavenLocal

custom maven repo:

- Gradle -> Tasks -> publish

### 3. shade

1. see `example.gradle.kts` and utilize in your project
2. Gradle -> Tasks -> shadowJar

# goals
- [ ] add comments
- [ ] make faster
    - [ ] minify gradient (0.992ms) ~0.500ms
    - [ ] convert gradients (0.105ms) ~0.100ms
    - [ ] minify hex (0.118ms) ~0.100ms
    - [x] convert hex (0.011ms) ~0.020ms