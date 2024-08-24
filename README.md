# color-converter-lib

i made converting minecraft color strings less complicated... for me

please don't use this on production; am refactoring it often.

feel free to fork it and use it in your code (why); see the license.

## Examples

String#convertColors().minifyColors()

```
&cHello &aworld! -> §cHello §aworld!

&#ff0000Hello &#00ff00world! -> §cHello §aworld!

{#ff0000}Hello {#0f0}world! -> §cHello §aworld!

{#black>}0123456   abcdef{#ffffff<} -> "§00§x§1§1§1§1§1§11§x§2§2§2§2§2§22§x§3§3§3§3§3§33§x§4§4§4§4§4§44§85§x§6§6§6§6§6§66   §7a§x§b§b§b§b§b§bb§x§c§c§c§c§c§cc§x§d§d§d§d§d§dd§x§e§e§e§e§e§ee§ff
```

i hope that made sense.

# Usage

as shown in the example

```
"&3my &4&lcool &#a0atext".convertColors().minifyColors()
```

### Shade this in your project

See example.gradle.kts.

# Goals

- [ ] add comments
- [ ] make faster
    - [ ] minify gradient (0.992ms) ~0.500ms
    - [ ] convert gradients (0.105ms) ~0.100ms
    - [ ] minify hex (0.118ms) ~0.100ms
    - [x] convert hex (0.011ms) ~0.020ms