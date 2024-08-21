# color-converter-lib
A library to convert and format colors from strings into Minecraft's color codes.

Please don't use this on production; am refactoring it often.

Feel free to fork it and use it in your code; see the license.

## Examples
```
&cHello &aworld! -> §cHello §aworld!

&#ff0000Hello &#00ff00world! -> §cHello §aworld!

{#ff0000}Hello {#0f0}world! -> §cHello §aworld!

{#black>}0123456   abcdef{#ffffff<} -> "§00§x§1§1§1§1§1§11§x§2§2§2§2§2§22§x§3§3§3§3§3§33§x§4§4§4§4§4§44§85§x§6§6§6§6§6§66   §7a§x§b§b§b§b§b§bb§x§c§c§c§c§c§cc§x§d§d§d§d§d§dd§x§e§e§e§e§e§ee§ff
```

# Usage
Everything can mostly be found in the parser class.

I'll add comments soon.

## Shade this in your project
See example.gradle.kts.