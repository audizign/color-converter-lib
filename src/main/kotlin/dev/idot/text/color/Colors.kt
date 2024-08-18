package dev.idot.text.color

import com.google.gson.Gson

enum class Colors(private val rgb: Int) {
    BLACK(0x000000),
    NAVYBLUE(0x000080),
    DARKBLUE(0x0000C8),
    BLUE(0x0000FF),
    STRATOS(0x000741),
    SWAMP(0x001B1C),
    RESOLUTIONBLUE(0x002387),
    DEEPFIR(0x002900),
    BURNHAM(0x002E20),
    INTERNATIONALKLEINBLUE(0x002FA7),
    PRUSSIANBLUE(0x003153),
    MIDNIGHTBLUE(0x003366),
    SMALT(0x003399),
    DEEPTEAL(0x003532),
    CYPRUS(0x003E40),
    KAITOKEGREEN(0x004620),
    COBALT(0x0047AB),
    CRUSOE(0x004816),
    SHERPABLUE(0x004950),
    ENDEAVOUR(0x0056A7),
    CAMARONE(0x00581A),
    SCIENCEBLUE(0x0066CC),
    BLUERIBBON(0x0066FF),
    TROPICALRAINFOREST(0x00755E),
    ALLPORTS(0x0076A3),
    DEEPCERULEAN(0x007BA7),
    LOCHMARA(0x007EC7),
    AZURERADIANCE(0x007FFF),
    TEAL(0x008080),
    BONDIBLUE(0x0095B6),
    PACIFICBLUE(0x009DC4),
    PERSIANGREEN(0x00A693),
    JADE(0x00A86B),
    CARIBBEANGREEN(0x00CC99),
    ROBINSEGGBLUE(0x00CCCC),
    GREEN(0x00FF00),
    SPRINGGREEN(0x00FF7F),
    CYAN(0x00FFFF),
    AQUA(0x00FFFF),
    BLUECHARCOAL(0x010D1A),
    MIDNIGHT(0x011635),
    HOLLY(0x011D13),
    DAINTREE(0x012731),
    CARDINGREEN(0x01361C),
    COUNTYGREEN(0x01371A),
    ASTRONAUTBLUE(0x013E62),
    REGALBLUE(0x013F6A),
    AQUADEEP(0x014B43),
    ORIENT(0x015E85),
    BLUESTONE(0x016162),
    FUNGREEN(0x016D39),
    PINEGREEN(0x01796F),
    BLUELAGOON(0x017987),
    DEEPSEA(0x01826B),
    GREENHAZE(0x01A368),
    ENGLISHHOLLY(0x022D15),
    SHERWOODGREEN(0x02402C),
    CONGRESSBLUE(0x02478E),
    EVENINGSEA(0x024E46),
    BAHAMABLUE(0x026395),
    OBSERVATORY(0x02866F),
    CERULEAN(0x02A4D3),
    TANGAROA(0x03163C),
    GREENVOGUE(0x032B52),
    MOSQUE(0x036A6E),
    MIDNIGHTMOSS(0x041004),
    BLACKPEARL(0x041322),
    BLUEWHALE(0x042E4C),
    ZUCCINI(0x044022),
    TEALBLUE(0x044259),
    DEEPCOVE(0x051040),
    GULFBLUE(0x051657),
    VENICEBLUE(0x055989),
    WATERCOURSE(0x056F57),
    CATALINABLUE(0x062A78),
    TIBER(0x063537),
    GOSSAMER(0x069B81),
    NIAGARA(0x06A189),
    TARAWERA(0x073A50),
    JAGUAR(0x080110),
    BLACKBEAN(0x081910),
    DEEPSAPPHIRE(0x082567),
    ELFGREEN(0x088370),
    BRIGHTTURQUOISE(0x08E8DE),
    DOWNRIVER(0x092256),
    PALMGREEN(0x09230F),
    MADISON(0x09255D),
    BOTTLEGREEN(0x093624),
    DEEPSEAGREEN(0x095859),
    SALEM(0x097F4B),
    BLACKRUSSIAN(0x0A001C),
    DARKFERN(0x0A480D),
    JAPANESELAUREL(0x0A6906),
    ATOLL(0x0A6F75),
    CODGRAY(0x0B0B0B),
    MARSHLAND(0x0B0F08),
    GORDONSGREEN(0x0B1107),
    BLACKFOREST(0x0B1304),
    SANFELIX(0x0B6207),
    MALACHITE(0x0BDA51),
    EBONY(0x0C0B1D),
    WOODSMOKE(0x0C0D0F),
    RACINGGREEN(0x0C1911),
    SURFIEGREEN(0x0C7A79),
    BLUECHILL(0x0C8990),
    BLACKROCK(0x0D0332),
    BUNKER(0x0D1117),
    AZTEC(0x0D1C19),
    BUSH(0x0D2E1C),
    CINDER(0x0E0E18),
    FIREFLY(0x0E2A30),
    TOREABAY(0x0F2D9E),
    VULCAN(0x10121D),
    GREENWATERLOO(0x101405),
    EDEN(0x105852),
    ARAPAWA(0x110C6C),
    ULTRAMARINE(0x120A8F),
    ELEPHANT(0x123447),
    JEWEL(0x126B40),
    DIESEL(0x130000),
    ASPHALT(0x130A06),
    BLUEZODIAC(0x13264D),
    PARSLEY(0x134F19),
    NERO(0x140600),
    TORYBLUE(0x1450AA),
    BUNTING(0x151F4C),
    DENIM(0x1560BD),
    GENOA(0x15736B),
    MIRAGE(0x161928),
    HUNTERGREEN(0x161D10),
    BIGSTONE(0x162A40),
    CELTIC(0x163222),
    TIMBERGREEN(0x16322C),
    GABLEGREEN(0x163531),
    PINETREE(0x171F04),
    CHATHAMSBLUE(0x175579),
    DEEPFORESTGREEN(0x182D09),
    BLUMINE(0x18587A),
    PALMLEAF(0x19330E),
    NILEBLUE(0x193751),
    FUNBLUE(0x1959A8),
    LUCKYPOINT(0x1A1A68),
    MOUNTAINMEADOW(0x1AB385),
    TOLOPEA(0x1B0245),
    HAITI(0x1B1035),
    DEEPKOAMARU(0x1B127B),
    ACADIA(0x1B1404),
    SEAWEED(0x1B2F11),
    BISCAY(0x1B3162),
    MATISSE(0x1B659D),
    CROWSHEAD(0x1C1208),
    RANGOONGREEN(0x1C1E13),
    PERSIANBLUE(0x1C39BB),
    EVERGLADE(0x1C402E),
    ELM(0x1C7C7D),
    GREENPEA(0x1D6142),
    CREOLE(0x1E0F04),
    KARAKA(0x1E1609),
    ELPASO(0x1E1708),
    CELLO(0x1E385B),
    TEPAPAGREEN(0x1E433C),
    DODGERBLUE(0x1E90FF),
    EASTERNBLUE(0x1E9AB0),
    NIGHTRIDER(0x1F120F),
    JAVA(0x1FC2C2),
    JACKSONSPURPLE(0x20208D),
    CLOUDBURST(0x202E54),
    BLUEDIANNE(0x204852),
    ETERNITY(0x211A0E),
    DEEPBLUE(0x220878),
    FORESTGREEN(0x228B22),
    MALLARD(0x233418),
    VIOLET(0x240A40),
    KILAMANJARO(0x240C02),
    LOGCABIN(0x242A1D),
    BLACKOLIVE(0x242E16),
    GREENHOUSE(0x24500F),
    GRAPHITE(0x251607),
    CANNONBLACK(0x251706),
    PORTGORE(0x251F4F),
    SHARK(0x25272C),
    GREENKELP(0x25311C),
    CURIOUSBLUE(0x2596D1),
    PAUA(0x260368),
    PARISM(0x26056A),
    WOODBARK(0x261105),
    GONDOLA(0x261414),
    STEELGRAY(0x262335),
    EBONYCLAY(0x26283B),
    BAYOFMANY(0x273A81),
    PLANTATION(0x27504B),
    EUCALYPTUS(0x278A5B),
    OIL(0x281E15),
    ASTRONAUT(0x283A77),
    MARINER(0x286ACD),
    VIOLENTVIOLET(0x290C5E),
    BASTILLE(0x292130),
    ZEUS(0x292319),
    CHARADE(0x292937),
    JELLYBEAN(0x297B9A),
    JUNGLEGREEN(0x29AB87),
    CHERRYPIE(0x2A0359),
    COFFEEBEAN(0x2A140E),
    BALTICSEA(0x2A2630),
    TURTLEGREEN(0x2A380B),
    CERULEANBLUE(0x2A52BE),
    SEPIABLACK(0x2B0202),
    VALHALLA(0x2B194F),
    HEAVYMETAL(0x2B3228),
    BLUEGEM(0x2C0E8C),
    REVOLVER(0x2C1632),
    BLEACHEDCEDAR(0x2C2133),
    LOCHINVAR(0x2C8C84),
    MIKADO(0x2D2510),
    OUTERSPACE(0x2D383A),
    STTROPAZ(0x2D569B),
    JACARANDA(0x2E0329),
    JACKOBEAN(0x2E1905),
    RANGITOTO(0x2E3222),
    RHINO(0x2E3F62),
    SEAGREEN(0x2E8B57),
    SCOOTER(0x2EBFD4),
    ONION(0x2F270E),
    GOVERNORBAY(0x2F3CB3),
    SAPPHIRE(0x2F519E),
    SPECTRA(0x2F5A57),
    CASAL(0x2F6168),
    MELANZANE(0x300529),
    COCOABROWN(0x301F1E),
    WOODRUSH(0x302A0F),
    SANJUAN(0x304B6A),
    TURQUOISE(0x30D5C8),
    ECLIPSE(0x311C17),
    PICKLEDBLUEWOOD(0x314459),
    AZURE(0x315BA1),
    CALYPSO(0x31728D),
    PARADISO(0x317D82),
    PERSIANINDIGO(0x32127A),
    BLACKCURRANT(0x32293A),
    MINESHAFT(0x323232),
    STROMBOLI(0x325D52),
    BILBAO(0x327C14),
    ASTRAL(0x327DA0),
    CHRISTALLE(0x33036B),
    THUNDER(0x33292F),
    SHAMROCK(0x33CC99),
    TAMARIND(0x341515),
    MARDIGRAS(0x350036),
    VALENTINO(0x350E42),
    JAGGER(0x350E57),
    TUNA(0x353542),
    CHAMBRAY(0x354E8C),
    MARTINIQUE(0x363050),
    TUATARA(0x363534),
    WAIOURU(0x363C0D),
    MING(0x36747D),
    LAPALMA(0x368716),
    CHOCOLATE(0x370202),
    CLINKER(0x371D09),
    BROWNTUMBLEWEED(0x37290E),
    BIRCH(0x373021),
    ORACLE(0x377475),
    BLUEDIAMOND(0x380474),
    GRAPE(0x381A51),
    DUNE(0x383533),
    OXFORDBLUE(0x384555),
    CLOVER(0x384910),
    LIMEDSPRUCE(0x394851),
    DELL(0x396413),
    TOLEDO(0x3A0020),
    SAMBUCA(0x3A2010),
    JACARTA(0x3A2A6A),
    WILLIAM(0x3A686C),
    KILLARNEY(0x3A6A47),
    KEPPEL(0x3AB09E),
    TEMPTRESS(0x3B000B),
    AUBERGINE(0x3B0910),
    JON(0x3B1F1F),
    TREEHOUSE(0x3B2820),
    AMAZON(0x3B7A57),
    BOSTONBLUE(0x3B91B4),
    WINDSOR(0x3C0878),
    REBEL(0x3C1206),
    METEORITE(0x3C1F76),
    DARKEBONY(0x3C2005),
    CAMOUFLAGE(0x3C3910),
    BRIGHTGRAY(0x3C4151),
    CAPECOD(0x3C4443),
    LUNARGREEN(0x3C493A),
    BEAN(0x3D0C02),
    BISTRE(0x3D2B1F),
    GOBLIN(0x3D7D52),
    KINGFISHERDAISY(0x3E0480),
    CEDAR(0x3E1C14),
    ENGLISHWALNUT(0x3E2B23),
    BLACKMARLIN(0x3E2C1C),
    SHIPGRAY(0x3E3A44),
    PELOROUS(0x3EABBF),
    BRONZE(0x3F2109),
    COLA(0x3F2500),
    MADRAS(0x3F3002),
    MINSK(0x3F307F),
    CABBAGEPONT(0x3F4C3A),
    TOMTHUMB(0x3F583B),
    MINERALGREEN(0x3F5D53),
    PUERTORICO(0x3FC1AA),
    HARLEQUIN(0x3FFF00),
    BROWNPOD(0x401801),
    CORK(0x40291D),
    MASALA(0x403B38),
    THATCHGREEN(0x403D19),
    FIORD(0x405169),
    VIRIDIAN(0x40826D),
    CHATEAUGREEN(0x40A860),
    RIPEPLUM(0x410056),
    PACO(0x411F10),
    DEEPOAK(0x412010),
    MERLIN(0x413C37),
    GUNPOWDER(0x414257),
    EASTBAY(0x414C7D),
    ROYALBLUE(0x4169E1),
    OCEANGREEN(0x41AA78),
    BURNTMAROON(0x420303),
    LISBONBROWN(0x423921),
    FADEDJADE(0x427977),
    SCARLETGUM(0x431560),
    IROKO(0x433120),
    ARMADILLO(0x433E37),
    RIVERBED(0x434C59),
    GREENLEAF(0x436A0D),
    BAROSSA(0x44012D),
    MOROCCOBROWN(0x441D00),
    MAKO(0x444954),
    KELP(0x454936),
    SANMARINO(0x456CAC),
    PICTONBLUE(0x45B1E8),
    LOULOU(0x460B41),
    CRATERBROWN(0x462425),
    GRAYASPARAGUS(0x465945),
    STEELBLUE(0x4682B4),
    RUSTICRED(0x480404),
    BULGARIANROSE(0x480607),
    CLAIRVOYANT(0x480656),
    COCOABEAN(0x481C1C),
    WOODYBROWN(0x483131),
    TAUPE(0x483C32),
    VANCLEEF(0x49170C),
    BROWNDERBY(0x492615),
    METALLICBRONZE(0x49371B),
    VERDUNGREEN(0x495400),
    BLUEBAYOUX(0x496679),
    BISMARK(0x497183),
    BRACKEN(0x4A2A04),
    DEEPBRONZE(0x4A3004),
    MONDO(0x4A3C30),
    TUNDORA(0x4A4244),
    GRAVEL(0x4A444B),
    TROUT(0x4A4E5A),
    PIGMENTINDIGO(0x4B0082),
    NANDOR(0x4B5D52),
    SADDLE(0x4C3024),
    ABBEY(0x4C4F56),
    BLACKBERRY(0x4D0135),
    CABSAV(0x4D0A18),
    INDIANTAN(0x4D1E01),
    COWBOY(0x4D282D),
    LIVIDBROWN(0x4D282E),
    ROCK(0x4D3833),
    PUNGA(0x4D3D14),
    BRONZETONE(0x4D400F),
    WOODLAND(0x4D5328),
    MAHOGANY(0x4E0606),
    BOSSANOVA(0x4E2A5A),
    MATTERHORN(0x4E3B41),
    BRONZEOLIVE(0x4E420C),
    MULLEDWINE(0x4E4562),
    AXOLOTL(0x4E6649),
    WEDGEWOOD(0x4E7F9E),
    SHAKESPEARE(0x4EABD1),
    HONEYFLOWER(0x4F1C70),
    DAISYBUSH(0x4F2398),
    INDIGO(0x4F69C6),
    FERNGREEN(0x4F7942),
    FRUITSALAD(0x4F9D5D),
    APPLE(0x4FA83D),
    MORTAR(0x504351),
    KASHMIRBLUE(0x507096),
    CUTTYSARK(0x507672),
    EMERALD(0x50C878),
    EMPEROR(0x514649),
    CHALETGREEN(0x516E3D),
    COMO(0x517C66),
    SMALTBLUE(0x51808F),
    CASTRO(0x52001F),
    MAROONOAK(0x520C17),
    GIGAS(0x523C94),
    VOODOO(0x533455),
    VICTORIA(0x534491),
    HIPPIEGREEN(0x53824B),
    HEATH(0x541012),
    JUDGEGRAY(0x544333),
    FUSCOUSGRAY(0x54534D),
    VIDALOCA(0x549019),
    CIOCCOLATO(0x55280C),
    SARATOGA(0x555B10),
    FINLANDIA(0x556D56),
    HAVELOCKBLUE(0x5590D9),
    FOUNTAINBLUE(0x56B4BE),
    SPRINGLEAVES(0x578363),
    SADDLEBROWN(0x583401),
    SCARPAFLOW(0x585562),
    CACTUS(0x587156),
    HIPPIEBLUE(0x589AAF),
    WINEBERRY(0x591D35),
    BROWNBRAMBLE(0x592804),
    CONGOBROWN(0x593737),
    MILLBROOK(0x594433),
    WAIKAWAGRAY(0x5A6E9C),
    HORIZON(0x5A87A0),
    JAMBALAYA(0x5B3013),
    BORDEAUX(0x5C0120),
    MULBERRYWOOD(0x5C0536),
    CARNABYTAN(0x5C2E01),
    COMET(0x5C5D75),
    REDWOOD(0x5D1E0F),
    DONJUAN(0x5D4C51),
    CHICAGO(0x5D5C58),
    VERDIGRIS(0x5D5E37),
    DINGLEY(0x5D7747),
    BREAKERBAY(0x5DA19F),
    KABUL(0x5E483E),
    HEMLOCK(0x5E5D3B),
    IRISHCOFFEE(0x5F3D26),
    MIDGRAY(0x5F5F6E),
    SHUTTLEGRAY(0x5F6672),
    AQUAFOREST(0x5FA777),
    TRADEWIND(0x5FB3AC),
    HORSESNECK(0x604913),
    SMOKY(0x605B73),
    CORDUROY(0x606E68),
    DANUBE(0x6093D1),
    ESPRESSO(0x612718),
    EGGPLANT(0x614051),
    COSTADELSOL(0x615D30),
    GLADEGREEN(0x61845F),
    BUCCANEER(0x622F30),
    QUINCY(0x623F2D),
    BUTTERFLYBUSH(0x624E9A),
    WESTCOAST(0x625119),
    FINCH(0x626649),
    PATINA(0x639A8F),
    FERN(0x63B76C),
    BLUEVIOLET(0x6456B7),
    DOLPHIN(0x646077),
    STORMDUST(0x646463),
    SIAM(0x646A54),
    NEVADA(0x646E75),
    CORNFLOWERBLUE(0x6495ED),
    VIKING(0x64CCDB),
    ROSEWOOD(0x65000B),
    CHERRYWOOD(0x651A14),
    PURPLEHEART(0x652DC1),
    FERNFROND(0x657220),
    WILLOWGROVE(0x65745D),
    HOKI(0x65869F),
    POMPADOUR(0x660045),
    PURPLE(0x660099),
    TYRIANPURPLE(0x66023C),
    DARKTAN(0x661010),
    SILVERTREE(0x66B58F),
    BRIGHTGREEN(0x66FF00),
    SCREAMINGREEN(0x66FF66),
    BLACKROSE(0x67032D),
    SCAMPI(0x675FA6),
    IRONSIDEGRAY(0x676662),
    VIRIDIANGREEN(0x678975),
    CHRISTI(0x67A712),
    NUTMEGWOODFINISH(0x683600),
    ZAMBEZI(0x685558),
    SALTBOX(0x685E6E),
    TAWNYPORT(0x692545),
    FINN(0x692D54),
    SCORPION(0x695F62),
    LYNCH(0x697E9A),
    SPICE(0x6A442E),
    HIMALAYA(0x6A5D1B),
    SOYABEAN(0x6A6051),
    HAIRYHEATH(0x6B2A14),
    ROYALPURPLE(0x6B3FA0),
    SHINGLEFAWN(0x6B4E31),
    DORADO(0x6B5755),
    BERMUDAGRAY(0x6B8BA2),
    OLIVEDRAB(0x6B8E23),
    EMINENCE(0x6C3082),
    TURQUOISEBLUE(0x6CDAE7),
    LONESTAR(0x6D0101),
    PINECONE(0x6D5E54),
    DOVEGRAY(0x6D6C6C),
    JUNIPER(0x6D9292),
    GOTHIC(0x6D92A1),
    REDOXIDE(0x6E0902),
    MOCCACCINO(0x6E1D14),
    PICKLEDBEAN(0x6E4826),
    DALLAS(0x6E4B26),
    KOKODA(0x6E6D57),
    PALESKY(0x6E7783),
    CAFEROYALE(0x6F440C),
    FLINT(0x6F6A61),
    HIGHLAND(0x6F8E63),
    LIMEADE(0x6F9D02),
    DOWNY(0x6FD0C5),
    PERSIANPLUM(0x701C1C),
    SEPIA(0x704214),
    ANTIQUEBRONZE(0x704A07),
    FERRA(0x704F50),
    COFFEE(0x706555),
    SLATEGRAY(0x708090),
    CEDARWOODFINISH(0x711A00),
    METALLICCOPPER(0x71291D),
    AFFAIR(0x714693),
    STUDIO(0x714AB2),
    TOBACCOBROWN(0x715D47),
    YELLOWMETAL(0x716338),
    PEAT(0x716B56),
    OLIVETONE(0x716E10),
    STORMGRAY(0x717486),
    SIROCCO(0x718080),
    AQUAMARINEBLUE(0x71D9E2),
    VENETIANRED(0x72010F),
    OLDCOPPER(0x724A2F),
    GOBEN(0x726D4E),
    RAVEN(0x727B89),
    SEANCE(0x731E8F),
    RAWUMBER(0x734A12),
    KIMBERLY(0x736C9F),
    CROCODILE(0x736D58),
    CRETE(0x737829),
    XANADU(0x738678),
    SPICYMUSTARD(0x74640D),
    LIMEDASH(0x747D63),
    ROLLINGSTONE(0x747D83),
    BLUESMOKE(0x748881),
    LAUREL(0x749378),
    MANTIS(0x74C365),
    RUSSETT(0x755A57),
    DELUGE(0x7563A8),
    COSMIC(0x76395D),
    BLUEMARGUERITE(0x7666C6),
    LIMA(0x76BD17),
    SKYBLUE(0x76D7EA),
    DARKBURGUNDY(0x770F05),
    CROWNOFTHORNS(0x771F1F),
    WALNUT(0x773F1A),
    PABLO(0x776F61),
    PACIFIKA(0x778120),
    OXLEY(0x779E86),
    PASTELGREEN(0x77DD77),
    JAPANESEMAPLE(0x780109),
    MOCHA(0x782D19),
    PEANUT(0x782F16),
    CAMOUFLAGEGREEN(0x78866B),
    WASABI(0x788A25),
    SHIPCOVE(0x788BBA),
    SEANYMPH(0x78A39C),
    ROMANCOFFEE(0x795D4C),
    OLDLAVENDER(0x796878),
    RUM(0x796989),
    FEDORA(0x796A78),
    SANDSTONE(0x796D62),
    SPRAY(0x79DEEC),
    SIREN(0x7A013A),
    FUCHSIABLUE(0x7A58C1),
    BOULDER(0x7A7A7A),
    WILDBLUEYONDER(0x7A89B8),
    DEYORK(0x7AC488),
    REDBEECH(0x7B3801),
    CINNAMON(0x7B3F00),
    YUKONGOLD(0x7B6608),
    TAPA(0x7B7874),
    WATERLOO(0x7B7C94),
    FLAXSMOKE(0x7B8265),
    AMULET(0x7B9F80),
    ASPARAGUS(0x7BA05B),
    KENYANCOPPER(0x7C1C05),
    PESTO(0x7C7631),
    TOPAZ(0x7C778A),
    CONCORD(0x7C7B7A),
    JUMBO(0x7C7B82),
    TRENDYGREEN(0x7C881A),
    GUMBO(0x7CA1A6),
    ACAPULCO(0x7CB0A1),
    NEPTUNE(0x7CB7BB),
    PUEBLO(0x7D2C14),
    BAYLEAF(0x7DA98D),
    MALIBU(0x7DC8F7),
    BERMUDA(0x7DD8C6),
    COPPERCANYON(0x7E3A15),
    CLARET(0x7F1734),
    PERUTAN(0x7F3A02),
    FALCON(0x7F626D),
    MOBSTER(0x7F7589),
    MOODYBLUE(0x7F76D3),
    CHARTREUSE(0x7FFF00),
    AQUAMARINE(0x7FFFD4),
    MAROON(0x800000),
    ROSEBUDCHERRY(0x800B47),
    FALURED(0x801818),
    REDROBIN(0x80341F),
    VIVIDVIOLET(0x803790),
    RUSSET(0x80461B),
    FRIARGRAY(0x807E79),
    OLIVE(0x808000),
    GRAY(0x808080),
    GULFSTREAM(0x80B3AE),
    GLACIER(0x80B3C4),
    SEAGULL(0x80CCEA),
    NUTMEG(0x81422C),
    SPICYPINK(0x816E71),
    EMPRESS(0x817377),
    SPANISHGREEN(0x819885),
    SANDDUNE(0x826F65),
    GUNSMOKE(0x828685),
    BATTLESHIPGRAY(0x828F72),
    MERLOT(0x831923),
    SHADOW(0x837050),
    CHELSEACUCUMBER(0x83AA5D),
    MONTECARLO(0x83D0C6),
    PLUM(0x843179),
    GRANNYSMITH(0x84A0A0),
    CHETWODEBLUE(0x8581D9),
    BANDICOOT(0x858470),
    BALIHAI(0x859FAF),
    HALFBAKED(0x85C4CC),
    REDDEVIL(0x860111),
    LOTUS(0x863C3C),
    IRONSTONE(0x86483C),
    BULLSHOT(0x864D1E),
    RUSTYNAIL(0x86560A),
    BITTER(0x868974),
    REGENTGRAY(0x86949F),
    DISCO(0x871550),
    AMERICANO(0x87756E),
    HURRICANE(0x877C7B),
    OSLOGRAY(0x878D91),
    SUSHI(0x87AB39),
    SPICYMIX(0x885342),
    KUMERA(0x886221),
    SUVAGRAY(0x888387),
    AVOCADO(0x888D65),
    CAMELOT(0x893456),
    SOLIDPINK(0x893843),
    CANNONPINK(0x894367),
    MAKARA(0x897D6D),
    BURNTUMBER(0x8A3324),
    TRUEV(0x8A73D6),
    CLAYCREEK(0x8A8360),
    MONSOON(0x8A8389),
    STACK(0x8A8F8A),
    JORDYBLUE(0x8AB9F1),
    ELECTRICVIOLET(0x8B00FF),
    MONARCH(0x8B0723),
    CORNHARVEST(0x8B6B0B),
    OLIVEHAZE(0x8B8470),
    SCHOONER(0x8B847E),
    NATURALGRAY(0x8B8680),
    MANTLE(0x8B9C90),
    PORTAGE(0x8B9FEE),
    ENVY(0x8BA690),
    CASCADE(0x8BA9A5),
    RIPTIDE(0x8BE6D8),
    CARDINALPINK(0x8C055E),
    MULEFAWN(0x8C472F),
    POTTERSCLAY(0x8C5738),
    TRENDYPINK(0x8C6495),
    PAPRIKA(0x8D0226),
    SANGUINEBROWN(0x8D3D38),
    TOSCA(0x8D3F3F),
    CEMENT(0x8D7662),
    GRANITEGREEN(0x8D8974),
    MANATEE(0x8D90A1),
    POLOBLUE(0x8DA8CC),
    REDBERRY(0x8E0000),
    ROPE(0x8E4D1E),
    OPIUM(0x8E6F70),
    DOMINO(0x8E775E),
    MAMBA(0x8E8190),
    NEPAL(0x8EABC1),
    POHUTUKAWA(0x8F021C),
    ELSALVA(0x8F3E33),
    KORMA(0x8F4B0E),
    SQUIRREL(0x8F8176),
    VISTABLUE(0x8FD6B4),
    BURGUNDY(0x900020),
    OLDBRICK(0x901E1E),
    HEMP(0x907874),
    ALMONDFROST(0x907B71),
    SYCAMORE(0x908D39),
    SANGRIA(0x92000A),
    CUMIN(0x924321),
    BEAVER(0x926F5B),
    STONEWALL(0x928573),
    VENUS(0x928590),
    MEDIUMPURPLE(0x9370DB),
    CORNFLOWER(0x93CCEA),
    ALGAEGREEN(0x93DFB8),
    COPPERRUST(0x944747),
    ARROWTOWN(0x948771),
    SCARLETT(0x950015),
    STRIKEMASTER(0x956387),
    MOUNTAINMIST(0x959396),
    CARMINE(0x960018),
    BROWN(0x964B00),
    LEATHER(0x967059),
    PURPLEMOUNTAINSMAJESTY(0x9678B6),
    LAVENDERPURPLE(0x967BB6),
    PEWTER(0x96A8A1),
    SUMMERGREEN(0x96BBAB),
    AUCHICO(0x97605D),
    WISTERIA(0x9771B5),
    ATLANTIS(0x97CD2D),
    VINROUGE(0x983D61),
    LILACBUSH(0x9874D3),
    BAZAAR(0x98777B),
    HACIENDA(0x98811B),
    PALEOYSTER(0x988D77),
    MINTGREEN(0x98FF98),
    FRESHEGGPLANT(0x990066),
    VIOLETEGGPLANT(0x991199),
    TAMARILLO(0x991613),
    TOTEMPOLE(0x991B07),
    COPPERROSE(0x996666),
    AMETHYST(0x9966CC),
    MOUNTBATTENPINK(0x997A8D),
    BLUEBELL(0x9999CC),
    PRAIRIESAND(0x9A3820),
    TOAST(0x9A6E61),
    GURKHA(0x9A9577),
    OLIVINE(0x9AB973),
    SHADOWGREEN(0x9AC2B8),
    OREGON(0x9B4703),
    LEMONGRASS(0x9B9E8F),
    STILETTO(0x9C3336),
    HAWAIIANTAN(0x9D5616),
    GULLGRAY(0x9DACB7),
    PISTACHIO(0x9DC209),
    GRANNYSMITHAPPLE(0x9DE093),
    ANAKIWA(0x9DE5FF),
    CHELSEAGEM(0x9E5302),
    SEPIASKIN(0x9E5B40),
    SAGE(0x9EA587),
    CITRON(0x9EA91F),
    ROCKBLUE(0x9EB1CD),
    MORNINGGLORY(0x9EDEE0),
    COGNAC(0x9F381D),
    REEFGOLD(0x9F821C),
    STARDUST(0x9F9F9C),
    SANTASGRAY(0x9FA0B1),
    SINBAD(0x9FD7D3),
    FEIJOA(0x9FDD8C),
    TABASCO(0xA02712),
    BUTTEREDRUM(0xA1750D),
    HITGRAY(0xA1ADB5),
    CITRUS(0xA1C50A),
    AQUAISLAND(0xA1DAD7),
    WATERLEAF(0xA1E9DE),
    FLIRT(0xA2006D),
    ROUGE(0xA23B6C),
    CAPEPALLISER(0xA26645),
    GRAYCHATEAU(0xA2AAB3),
    EDWARD(0xA2AEAB),
    PHARLAP(0xA3807B),
    AMETHYSTSMOKE(0xA397B4),
    BLIZZARDBLUE(0xA3E3ED),
    DELTA(0xA4A49D),
    WISTFUL(0xA4A6D3),
    GREENSMOKE(0xA4AF6E),
    JAZZBERRYJAM(0xA50B5E),
    ZORBA(0xA59B91),
    BAHIA(0xA5CB0C),
    ROOFTERRACOTTA(0xA62F20),
    PAARL(0xA65529),
    BARLEYCORN(0xA68B5B),
    DONKEYBROWN(0xA69279),
    DAWN(0xA6A29A),
    MEXICANRED(0xA72525),
    LUXORGOLD(0xA7882C),
    RICHGOLD(0xA85307),
    RENOSAND(0xA86515),
    CORALTREE(0xA86B6B),
    DUSTYGRAY(0xA8989B),
    DULLLAVENDER(0xA899E6),
    TALLOW(0xA8A589),
    BUD(0xA8AE9C),
    LOCUST(0xA8AF8E),
    NORWAY(0xA8BD9F),
    CHINOOK(0xA8E3BD),
    GRAYOLIVE(0xA9A491),
    ALUMINIUM(0xA9ACB6),
    CADETBLUE(0xA9B2C3),
    SCHIST(0xA9B497),
    TOWERGRAY(0xA9BDBF),
    PERANO(0xA9BEF2),
    OPAL(0xA9C6C2),
    NIGHTSHADZ(0xAA375A),
    FIRE(0xAA4203),
    MUESLI(0xAA8B5B),
    SANDAL(0xAA8D6F),
    SHADYLADY(0xAAA5A9),
    LOGAN(0xAAA9CD),
    SPUNPEARL(0xAAABB7),
    REGENTSTBLUE(0xAAD6E6),
    MAGICMINT(0xAAF0D1),
    LIPSTICK(0xAB0563),
    ROYALHEATH(0xAB3472),
    SANDRIFT(0xAB917A),
    COLDPURPLE(0xABA0D9),
    BRONCO(0xABA196),
    LIMEDOAK(0xAC8A56),
    EASTSIDE(0xAC91CE),
    LEMONGINGER(0xAC9E22),
    NAPA(0xACA494),
    HILLARY(0xACA586),
    CLOUDY(0xACA59F),
    SILVERCHALICE(0xACACAC),
    SWAMPGREEN(0xACB78E),
    SPRINGRAIN(0xACCBB1),
    CONIFER(0xACDD4D),
    CELADON(0xACE1AF),
    MANDALAY(0xAD781B),
    CASPER(0xADBED1),
    MOSSGREEN(0xADDFAD),
    PADUA(0xADE6C4),
    GREENYELLOW(0xADFF2F),
    HIPPIEPINK(0xAE4560),
    DESERT(0xAE6020),
    BOUQUET(0xAE809E),
    MEDIUMCARMINE(0xAF4035),
    APPLEBLOSSOM(0xAF4D43),
    BROWNRUST(0xAF593E),
    DRIFTWOOD(0xAF8751),
    ALPINE(0xAF8F2C),
    LUCKY(0xAF9F1C),
    MARTINI(0xAFA09E),
    BOMBAY(0xAFB1B8),
    PIGEONPOST(0xAFBDD9),
    CADILLAC(0xB04C6A),
    MATRIX(0xB05D54),
    TAPESTRY(0xB05E81),
    MAITAI(0xB06608),
    DELRIO(0xB09A95),
    POWDERBLUE(0xB0E0E6),
    INCHWORM(0xB0E313),
    BRIGHTRED(0xB10000),
    VESUVIUS(0xB14A0B),
    PUMPKINSKIN(0xB1610B),
    SANTAFE(0xB16D52),
    TEAK(0xB19461),
    FRINGYFLOWER(0xB1E2C1),
    ICECOLD(0xB1F4E7),
    SHIRAZ(0xB20931),
    BILOBAFLOWER(0xB2A1EA),
    TALLPOPPY(0xB32D29),
    FIERYORANGE(0xB35213),
    HOTTODDY(0xB38007),
    TAUPEGRAY(0xB3AF95),
    LARIOJA(0xB3C110),
    WELLREAD(0xB43332),
    BLUSH(0xB44668),
    JUNGLEMIST(0xB4CFD3),
    TURKISHROSE(0xB57281),
    LAVENDER(0xB57EDC),
    MONGOOSE(0xB5A27F),
    OLIVEGREEN(0xB5B35C),
    JETSTREAM(0xB5D2CE),
    CRUISE(0xB5ECDF),
    HIBISCUS(0xB6316C),
    THATCH(0xB69D98),
    HEATHEREDGRAY(0xB6B095),
    EAGLE(0xB6BAA4),
    SPINDLE(0xB6D1EA),
    GUMLEAF(0xB6D3BF),
    RUST(0xB7410E),
    MUDDYWATERS(0xB78E5C),
    SAHARA(0xB7A214),
    HUSK(0xB7A458),
    NOBEL(0xB7B1B1),
    HEATHER(0xB7C3D0),
    MADANG(0xB7F0BE),
    MILANORED(0xB81104),
    COPPER(0xB87333),
    GIMBLET(0xB8B56A),
    GREENSPRING(0xB8C1B1),
    CELERY(0xB8C25D),
    SAIL(0xB8E0F9),
    CHESTNUT(0xB94E48),
    CRAIL(0xB95140),
    MARIGOLD(0xB98D28),
    WILDWILLOW(0xB9C46A),
    RAINEE(0xB9C8AC),
    GUARDSMANRED(0xBA0101),
    ROCKSPRAY(0xBA450C),
    BOURBON(0xBA6F1E),
    PIRATEGOLD(0xBA7F03),
    NOMAD(0xBAB1A2),
    SUBMARINE(0xBAC7C9),
    CHARLOTTE(0xBAEEF9),
    MEDIUMREDVIOLET(0xBB3385),
    BRANDYROSE(0xBB8983),
    RIOGRANDE(0xBBD009),
    SURF(0xBBD7C1),
    POWDERASH(0xBCC9C2),
    TUSCANY(0xBD5E2E),
    QUICKSAND(0xBD978E),
    SILK(0xBDB1A8),
    MALTA(0xBDB2A1),
    CHATELLE(0xBDB3C7),
    LAVENDERGRAY(0xBDBBD7),
    FRENCHGRAY(0xBDBDC6),
    CLAYASH(0xBDC8B3),
    LOBLOLLY(0xBDC9CE),
    FRENCHPASS(0xBDEDFD),
    LONDONHUE(0xBEA6C3),
    PINKSWAN(0xBEB5B7),
    FUEGO(0xBEDE0D),
    ROSEOFSHARON(0xBF5500),
    TIDE(0xBFB8B0),
    BLUEHAZE(0xBFBED8),
    SILVERSAND(0xBFC1C2),
    KEYLIMEPIE(0xBFC921),
    ZIGGURAT(0xBFDBE2),
    LIME(0xBFFF00),
    THUNDERBIRD(0xC02B18),
    MOJO(0xC04737),
    OLDROSE(0xC08081),
    SILVER(0xC0C0C0),
    PALELEAF(0xC0D3B9),
    PIXIEGREEN(0xC0D8B6),
    TIAMARIA(0xC1440E),
    FUCHSIAPINK(0xC154C1),
    BUDDHAGOLD(0xC1A004),
    BISONHIDE(0xC1B7A4),
    TEA(0xC1BAB0),
    GRAYSUIT(0xC1BECD),
    SPROUT(0xC1D7B0),
    SULU(0xC1F07C),
    INDOCHINE(0xC26B03),
    TWINE(0xC2955D),
    COTTONSEED(0xC2BDB6),
    PUMICE(0xC2CAC4),
    JAGGEDICE(0xC2E8E5),
    MAROONFLUSH(0xC32148),
    INDIANKHAKI(0xC3B091),
    PALESLATE(0xC3BFC1),
    GRAYNICKEL(0xC3C3BD),
    PERIWINKLEGRAY(0xC3CDE6),
    TIARA(0xC3D1D1),
    TROPICALBLUE(0xC3DDF9),
    CARDINAL(0xC41E3A),
    FUZZYWUZZYBROWN(0xC45655),
    ORANGEROUGHY(0xC45719),
    MISTGRAY(0xC4C4BC),
    CORIANDER(0xC4D0B0),
    MINTTULIP(0xC4F4EB),
    MULBERRY(0xC54B8C),
    NUGGET(0xC59922),
    TUSSOCK(0xC5994B),
    SEAMIST(0xC5DBCA),
    YELLOWGREEN(0xC5E17A),
    BRICKRED(0xC62D42),
    CONTESSA(0xC6726B),
    ORIENTALPINK(0xC69191),
    ROTI(0xC6A84B),
    ASH(0xC6C3B5),
    KANGAROO(0xC6C8BD),
    LASPALMAS(0xC6E610),
    MONZA(0xC7031E),
    REDVIOLET(0xC71585),
    CORALREEF(0xC7BCA2),
    MELROSE(0xC7C1FF),
    CLOUD(0xC7C4BF),
    GHOST(0xC7C9D5),
    PINEGLADE(0xC7CD90),
    BOTTICELLI(0xC7DDE5),
    ANTIQUEBRASS(0xC88A65),
    LILAC(0xC8A2C8),
    HOKEYPOKEY(0xC8A528),
    LILY(0xC8AABF),
    LASER(0xC8B568),
    EDGEWATER(0xC8E3D7),
    PIPER(0xC96323),
    PIZZA(0xC99415),
    LIGHTWISTERIA(0xC9A0DC),
    RODEODUST(0xC9B29B),
    SUNDANCE(0xC9B35B),
    EARLSGREEN(0xC9B93B),
    SILVERRUST(0xC9C0BB),
    CONCH(0xC9D9D2),
    REEF(0xC9FFA2),
    AEROBLUE(0xC9FFE5),
    FLUSHMAHOGANY(0xCA3435),
    TURMERIC(0xCABB48),
    PARISWHITE(0xCADCD4),
    BITTERLEMON(0xCAE00D),
    SKEPTIC(0xCAE6DA),
    VIOLA(0xCB8FA9),
    FOGGYGRAY(0xCBCAB6),
    GREENMIST(0xCBD3B0),
    NEBULA(0xCBDBD6),
    PERSIANRED(0xCC3333),
    BURNTORANGE(0xCC5500),
    OCHRE(0xCC7722),
    PUCE(0xCC8899),
    THISTLEGREEN(0xCCCAA8),
    PERIWINKLE(0xCCCCFF),
    ELECTRICLIME(0xCCFF00),
    TENN(0xCD5700),
    CHESTNUTROSE(0xCD5C5C),
    BRANDYPUNCH(0xCD8429),
    ONAHAU(0xCDF4FF),
    SORRELLBROWN(0xCEB98F),
    COLDTURKEY(0xCEBABA),
    YUMA(0xCEC291),
    CHINO(0xCEC7A7),
    EUNRY(0xCFA39D),
    OLDGOLD(0xCFB53B),
    TASMAN(0xCFDCCF),
    SURFCREST(0xCFE5D2),
    HUMMINGBIRD(0xCFF9F3),
    SCANDAL(0xCFFAF4),
    REDSTAGE(0xD05F04),
    HOPBUSH(0xD06DA1),
    METEOR(0xD07D12),
    PERFUME(0xD0BEF8),
    PRELUDE(0xD0C0E5),
    TEAGREEN(0xD0F0C0),
    GEEBUNG(0xD18F1B),
    VANILLA(0xD1BEA8),
    SOFTAMBER(0xD1C6B4),
    CELESTE(0xD1D2CA),
    MISCHKA(0xD1D2DD),
    PEAR(0xD1E231),
    HOTCINNAMON(0xD2691E),
    RAWSIENNA(0xD27D46),
    CAREYSPINK(0xD29EAA),
    TAN(0xD2B48C),
    DECO(0xD2DA97),
    BLUEROMANCE(0xD2F6DE),
    GOSSIP(0xD2F8B0),
    SISAL(0xD3CBBA),
    SWIRL(0xD3CDC5),
    CHARM(0xD47494),
    CLAMSHELL(0xD4B6AF),
    STRAW(0xD4BF8D),
    AKAROA(0xD4C4A8),
    BIRDFLOWER(0xD4CD16),
    IRON(0xD4D7D9),
    GEYSER(0xD4DFE2),
    HAWKESBLUE(0xD4E2FC),
    GRENADIER(0xD54600),
    CANCAN(0xD591A4),
    WHISKEY(0xD59A6F),
    WINTERHAZEL(0xD5D195),
    GRANNYAPPLE(0xD5F6E3),
    MYPINK(0xD69188),
    TACHA(0xD6C562),
    MOONRAKER(0xD6CEF6),
    QUILLGRAY(0xD6D6D1),
    SNOWYMINT(0xD6FFDB),
    NEWYORKPINK(0xD7837F),
    PAVLOVA(0xD7C498),
    FOG(0xD7D0FF),
    VALENCIA(0xD84437),
    JAPONICA(0xD87C63),
    THISTLE(0xD8BFD8),
    MAVERICK(0xD8C2D5),
    FOAM(0xD8FCFA),
    CABARET(0xD94972),
    BURNINGSAND(0xD99376),
    CAMEO(0xD9B99B),
    TIMBERWOLF(0xD9D6CF),
    TANA(0xD9DCC1),
    LINKWATER(0xD9E4F5),
    MABEL(0xD9F7FF),
    CERISE(0xDA3287),
    FLAMEPEA(0xDA5B38),
    BAMBOO(0xDA6304),
    REDDAMASK(0xDA6A41),
    ORCHID(0xDA70D6),
    COPPERFIELD(0xDA8A67),
    GOLDENGRASS(0xDAA520),
    ZANAH(0xDAECD6),
    ICEBERG(0xDAF4F0),
    OYSTERBAY(0xDAFAFF),
    CRANBERRY(0xDB5079),
    PETITEORCHID(0xDB9690),
    DISERRIA(0xDB995E),
    ALTO(0xDBDBDB),
    FROSTEDMINT(0xDBFFF8),
    CRIMSON(0xDC143C),
    PUNCH(0xDC4333),
    GALLIANO(0xDCB20C),
    BLOSSOM(0xDCB4BC),
    WATTLE(0xDCD747),
    WESTAR(0xDCD9D2),
    MOONMIST(0xDCDDCC),
    CAPER(0xDCEDB4),
    SWANSDOWN(0xDCF0EA),
    SWISSCOFFEE(0xDDD6D5),
    WHITEICE(0xDDF9F1),
    CERISERED(0xDE3163),
    ROMAN(0xDE6360),
    TUMBLEWEED(0xDEA681),
    GOLDTIPS(0xDEBA13),
    BRANDY(0xDEC196),
    WAFER(0xDECBC6),
    SAPLING(0xDED4A4),
    BARBERRY(0xDED717),
    BERYLGREEN(0xDEE5C0),
    PATTENSBLUE(0xDEF5FF),
    HELIOTROPE(0xDF73FF),
    APACHE(0xDFBE6F),
    CHENIN(0xDFCD6F),
    LOLA(0xDFCFDB),
    WILLOWBROOK(0xDFECDA),
    CHARTREUSEYELLOW(0xDFFF00),
    MAUVE(0xE0B0FF),
    ANZAC(0xE0B646),
    HARVESTGOLD(0xE0B974),
    CALICO(0xE0C095),
    BABYBLUE(0xE0FFFF),
    SUNGLO(0xE16865),
    EQUATOR(0xE1BC64),
    PINKFLARE(0xE1C0C8),
    PERIGLACIALBLUE(0xE1E6D6),
    KIDNAPPER(0xE1EAD4),
    TARA(0xE1F6E8),
    MANDY(0xE25465),
    TERRACOTTA(0xE2725B),
    GOLDENBELL(0xE28913),
    SHOCKING(0xE292C0),
    DIXIE(0xE29418),
    LIGHTORCHID(0xE29CD2),
    SNUFF(0xE2D8ED),
    MYSTIC(0xE2EBED),
    APPLEGREEN(0xE2F3EC),
    RAZZMATAZZ(0xE30B5C),
    ALIZARINCRIMSON(0xE32636),
    CINNABAR(0xE34234),
    CAVERNPINK(0xE3BEBE),
    PEPPERMINT(0xE3F5E1),
    MINDARO(0xE3F988),
    DEEPBLUSH(0xE47698),
    GAMBOGE(0xE49B0F),
    MELANIE(0xE4C2D5),
    TWILIGHT(0xE4CFDE),
    BONE(0xE4D1C0),
    SUNFLOWER(0xE4D422),
    GRAINBROWN(0xE4D5B7),
    ZOMBIE(0xE4D69B),
    FROSTEE(0xE4F6E7),
    SNOWFLURRY(0xE4FFD1),
    AMARANTH(0xE52B50),
    ZEST(0xE5841B),
    DUSTSTORM(0xE5CCC9),
    STARKWHITE(0xE5D7BD),
    HAMPTON(0xE5D8AF),
    BONJOUR(0xE5E0E1),
    MERCURY(0xE5E5E5),
    POLAR(0xE5F9F6),
    TRINIDAD(0xE64E03),
    GOLDSAND(0xE6BE8A),
    CASHMERE(0xE6BEA5),
    DOUBLESPANISHWHITE(0xE6D7B9),
    SATINLINEN(0xE6E4D4),
    HARP(0xE6F2EA),
    OFFGREEN(0xE6F8F3),
    HINTOFGREEN(0xE6FFE9),
    TRANQUIL(0xE6FFFF),
    MANGOTANGO(0xE77200),
    CHRISTINE(0xE7730A),
    TONYSPINK(0xE79F8C),
    KOBI(0xE79FC4),
    ROSEFOG(0xE7BCB4),
    CORN(0xE7BF05),
    PUTTY(0xE7CD8C),
    GRAYNURSE(0xE7ECE6),
    LILYWHITE(0xE7F8FF),
    BUBBLES(0xE7FEFF),
    FIREBUSH(0xE89928),
    SHILO(0xE8B9B3),
    PEARLBUSH(0xE8E0D5),
    GREENWHITE(0xE8EBE0),
    CHROMEWHITE(0xE8F1D4),
    GIN(0xE8F2EB),
    AQUASQUEEZE(0xE8F5F2),
    CLEMENTINE(0xE96E00),
    BURNTSIENNA(0xE97451),
    TAHITIGOLD(0xE97C07),
    OYSTERPINK(0xE9CECD),
    CONFETTI(0xE9D75A),
    EBB(0xE9E3E3),
    OTTOMAN(0xE9F8ED),
    CLEARDAY(0xE9FFFD),
    CARISSMA(0xEA88A8),
    PORSCHE(0xEAAE69),
    TULIPTREE(0xEAB33B),
    ROBROY(0xEAC674),
    RAFFIA(0xEADAB8),
    WHITEROCK(0xEAE8D4),
    PANACHE(0xEAF6EE),
    SOLITUDE(0xEAF6FF),
    AQUASPRING(0xEAF9F5),
    DEW(0xEAFFFE),
    APRICOT(0xEB9373),
    ZINNWALDITE(0xEBC2AF),
    FUELYELLOW(0xECA927),
    RONCHI(0xECC54E),
    FRENCHLILAC(0xECC7EE),
    JUSTRIGHT(0xECCDB9),
    WILDRICE(0xECE090),
    FALLGREEN(0xECEBBD),
    ATHSSPECIAL(0xECEBCE),
    STARSHIP(0xECF245),
    REDRIBBON(0xED0A3F),
    TANGO(0xED7A1C),
    CARROTORANGE(0xED9121),
    SEAPINK(0xED989E),
    TACAO(0xEDB381),
    DESERTSAND(0xEDC9AF),
    PANCHO(0xEDCDAB),
    CHAMOIS(0xEDDCB1),
    PRIMROSE(0xEDEA99),
    FROST(0xEDF5DD),
    AQUAHAZE(0xEDF5F5),
    ZUMTHOR(0xEDF6FF),
    NARVIK(0xEDF9F1),
    HONEYSUCKLE(0xEDFC84),
    LAVENDERMAGENTA(0xEE82EE),
    BEAUTYBUSH(0xEEC1BE),
    CHALKY(0xEED794),
    ALMOND(0xEED9C4),
    FLAX(0xEEDC82),
    BIZARRE(0xEEDEDA),
    DOUBLECOLONIALWHITE(0xEEE3AD),
    CARARRA(0xEEEEE8),
    MANZ(0xEEEF78),
    TAHUNASANDS(0xEEF0C8),
    ATHENSGRAY(0xEEF0F3),
    TUSK(0xEEF3C3),
    LOAFER(0xEEF4DE),
    CATSKILLWHITE(0xEEF6F7),
    TWILIGHTBLUE(0xEEFDFF),
    JONQUIL(0xEEFF9A),
    RICEFLOWER(0xEEFFE2),
    JAFFA(0xEF863F),
    GALLERY(0xEFEFEF),
    PORCELAIN(0xEFF2F3),
    MAUVELOUS(0xF091A9),
    GOLDENDREAM(0xF0D52D),
    GOLDENSAND(0xF0DB7D),
    BUFF(0xF0DC82),
    PRIM(0xF0E2EC),
    KHAKI(0xF0E68C),
    SELAGO(0xF0EEFD),
    TITANWHITE(0xF0EEFF),
    ALICEBLUE(0xF0F8FF),
    FETA(0xF0FCEA),
    GOLDDROP(0xF18200),
    WEWAK(0xF19BAB),
    SAHARASAND(0xF1E788),
    PARCHMENT(0xF1E9D2),
    BLUECHALK(0xF1E9FF),
    MINTJULEP(0xF1EEC1),
    SEASHELL(0xF1F1F1),
    SALTPAN(0xF1F7F2),
    TIDAL(0xF1FFAD),
    CHIFFON(0xF1FFC8),
    FLAMINGO(0xF2552A),
    TANGERINE(0xF28500),
    MANDYSPINK(0xF2C3B2),
    CONCRETE(0xF2F2F2),
    BLACKSQUEEZE(0xF2FAFA),
    POMEGRANATE(0xF34723),
    BUTTERCUP(0xF3AD16),
    NEWORLEANS(0xF3D69D),
    VANILLAICE(0xF3D9DF),
    SIDECAR(0xF3E7BB),
    DAWNPINK(0xF3E9E5),
    WHEATFIELD(0xF3EDCF),
    CANARY(0xF3FB62),
    ORINOCO(0xF3FBD4),
    CARLA(0xF3FFD8),
    HOLLYWOODCERISE(0xF400A1),
    SANDYBROWN(0xF4A460),
    SAFFRON(0xF4C430),
    RIPELEMON(0xF4D81C),
    JANNA(0xF4EBD3),
    PAMPAS(0xF4F2EE),
    WILDSAND(0xF4F4F4),
    ZIRCON(0xF4F8FF),
    FROLY(0xF57584),
    CREAMCAN(0xF5C85C),
    MANHATTAN(0xF5C999),
    MAIZE(0xF5D5A0),
    WHEAT(0xF5DEB3),
    SANDWISP(0xF5E7A2),
    POTPOURRI(0xF5E7E2),
    ALBESCENTWHITE(0xF5E9D3),
    SOFTPEACH(0xF5EDEF),
    ECRUWHITE(0xF5F3E5),
    BEIGE(0xF5F5DC),
    GOLDENFIZZ(0xF5FB3D),
    AUSTRALIANMINT(0xF5FFBE),
    FRENCHROSE(0xF64A8A),
    BRILLIANTROSE(0xF653A6),
    ILLUSION(0xF6A4C9),
    MERINO(0xF6F0E6),
    BLACKHAZE(0xF6F7F7),
    SPRINGSUN(0xF6FFDC),
    VIOLETRED(0xF7468A),
    CHILEANFIRE(0xF77703),
    PERSIANPINK(0xF77FBE),
    RAJAH(0xF7B668),
    AZALEA(0xF7C8DA),
    WEPEEP(0xF7DBE6),
    QUARTERSPANISHWHITE(0xF7F2E1),
    WHISPER(0xF7F5FA),
    SNOWDRIFT(0xF7FAF7),
    CASABLANCA(0xF8B853),
    CHANTILLY(0xF8C3DF),
    CHERUB(0xF8D9E9),
    MARZIPAN(0xF8DB9D),
    ENERGYYELLOW(0xF8DD5C),
    GIVRY(0xF8E4BF),
    WHITELINEN(0xF8F0E8),
    MAGNOLIA(0xF8F4FF),
    SPRINGWOOD(0xF8F6F1),
    COCONUTCREAM(0xF8F7DC),
    WHITELILAC(0xF8F7FC),
    DESERTSTORM(0xF8F8F7),
    TEXAS(0xF8F99C),
    CORNFIELD(0xF8FACD),
    MIMOSA(0xF8FDD3),
    CARNATION(0xF95A61),
    SAFFRONMANGO(0xF9BF58),
    CAROUSELPINK(0xF9E0ED),
    DAIRYCREAM(0xF9E4BC),
    PORTICA(0xF9E663),
    AMOUR(0xF9EAF3),
    RUMSWIZZLE(0xF9F8E4),
    DOLLY(0xF9FF8B),
    SUGARCANE(0xF9FFF6),
    ECSTASY(0xFA7814),
    TANHIDE(0xFA9D5A),
    CORVETTE(0xFAD3A2),
    PEACHYELLOW(0xFADFAD),
    TURBO(0xFAE600),
    ASTRA(0xFAEAB9),
    CHAMPAGNE(0xFAECCC),
    LINEN(0xFAF0E6),
    FANTASY(0xFAF3F0),
    CITRINEWHITE(0xFAF7D6),
    ALABASTER(0xFAFAFA),
    HINTOFYELLOW(0xFAFDE4),
    MILAN(0xFAFFA4),
    BRINKPINK(0xFB607F),
    GERALDINE(0xFB8989),
    LAVENDERROSE(0xFBA0E3),
    SEABUCKTHORN(0xFBA129),
    SUN(0xFBAC13),
    LAVENDERPINK(0xFBAED2),
    ROSEBUD(0xFBB2A3),
    CUPID(0xFBBEDA),
    CLASSICROSE(0xFBCCE7),
    APRICOTPEACH(0xFBCEB1),
    BANANAMANIA(0xFBE7B2),
    MARIGOLDYELLOW(0xFBE870),
    FESTIVAL(0xFBE96C),
    SWEETCORN(0xFBEA8C),
    CANDYCORN(0xFBEC5D),
    HINTOFRED(0xFBF9F9),
    SHALIMAR(0xFBFFBA),
    SHOCKINGPINK(0xFC0FC0),
    TICKLEMEPINK(0xFC80A5),
    TREEPOPPY(0xFC9C1D),
    LIGHTNINGYELLOW(0xFCC01E),
    GOLDENROD(0xFCD667),
    CANDLELIGHT(0xFCD917),
    CHEROKEE(0xFCDA98),
    DOUBLEPEARLLUSTA(0xFCF4D0),
    PEARLLUSTA(0xFCF4DC),
    VISTAWHITE(0xFCF8F7),
    BIANCA(0xFCFBF3),
    MOONGLOW(0xFCFEDA),
    CHINAIVORY(0xFCFFE7),
    CERAMIC(0xFCFFF9),
    TORCHRED(0xFD0E35),
    WILDWATERMELON(0xFD5B78),
    CRUSTA(0xFD7B33),
    SORBUS(0xFD7C07),
    SWEETPINK(0xFD9FA2),
    LIGHTAPRICOT(0xFDD5B1),
    PIGPINK(0xFDD7E4),
    CINDERELLA(0xFDE1DC),
    GOLDENGLOW(0xFDE295),
    LEMON(0xFDE910),
    OLDLACE(0xFDF5E6),
    HALFCOLONIALWHITE(0xFDF6D3),
    DROVER(0xFDF7AD),
    PALEPRIM(0xFDFEB8),
    CUMULUS(0xFDFFD5),
    PERSIANROSE(0xFE28A2),
    SUNSETORANGE(0xFE4C40),
    BITTERSWEET(0xFE6F5E),
    CALIFORNIA(0xFE9D04),
    YELLOWSEA(0xFEA904),
    MELON(0xFEBAAD),
    BRIGHTSUN(0xFED33C),
    DANDELION(0xFED85D),
    SALOMIE(0xFEDB8D),
    CAPEHONEY(0xFEE5AC),
    REMY(0xFEEBF3),
    OASIS(0xFEEFCE),
    BRIDESMAID(0xFEF0EC),
    BEESWAX(0xFEF2C7),
    BLEACHWHITE(0xFEF3D8),
    PIPI(0xFEF4CC),
    HALFSPANISHWHITE(0xFEF4DB),
    WISPPINK(0xFEF4F8),
    PROVINCIALPINK(0xFEF5F1),
    HALFDUTCHWHITE(0xFEF7DE),
    SOLITAIRE(0xFEF8E2),
    WHITEPOINTER(0xFEF8FF),
    OFFYELLOW(0xFEF9E3),
    ORANGEWHITE(0xFEFCED),
    RED(0xFF0000),
    ROSE(0xFF007F),
    PURPLEPIZZAZZ(0xFF00CC),
    MAGENTA(0xFF00FF),
    FUCHSIA(0xFF00FF),
    SCARLET(0xFF2400),
    WILDSTRAWBERRY(0xFF3399),
    RAZZLEDAZZLEROSE(0xFF33CC),
    RADICALRED(0xFF355E),
    REDORANGE(0xFF3F34),
    CORALRED(0xFF4040),
    VERMILION(0xFF4D00),
    INTERNATIONALORANGE(0xFF4F00),
    OUTRAGEOUSORANGE(0xFF6037),
    BLAZEORANGE(0xFF6600),
    PINKFLAMINGO(0xFF66FF),
    ORANGE(0xFF681F),
    HOTPINK(0xFF69B4),
    PERSIMMON(0xFF6B53),
    BLUSHPINK(0xFF6FFF),
    BURNINGORANGE(0xFF7034),
    PUMPKIN(0xFF7518),
    FLAMENCO(0xFF7D07),
    FLUSHORANGE(0xFF7F00),
    CORAL(0xFF7F50),
    SALMON(0xFF8C69),
    PIZAZZ(0xFF9000),
    WESTSIDE(0xFF910F),
    PINKSALMON(0xFF91A4),
    NEONCARROT(0xFF9933),
    ATOMICTANGERINE(0xFF9966),
    VIVIDTANGERINE(0xFF9980),
    SUNSHADE(0xFF9E2C),
    ORANGEPEEL(0xFFA000),
    MONALISA(0xFFA194),
    WEBORANGE(0xFFA500),
    CARNATIONPINK(0xFFA6C9),
    HITPINK(0xFFAB81),
    YELLOWORANGE(0xFFAE42),
    CORNFLOWERLILAC(0xFFB0AC),
    SUNDOWN(0xFFB1B3),
    MYSIN(0xFFB31F),
    TEXASROSE(0xFFB555),
    COTTONCANDY(0xFFB7D5),
    MACARONIANDCHEESE(0xFFB97B),
    SELECTIVEYELLOW(0xFFBA00),
    KOROMIKO(0xFFBD5F),
    AMBER(0xFFBF00),
    WAXFLOWER(0xFFC0A8),
    PINK(0xFFC0CB),
    YOURPINK(0xFFC3C0),
    SUPERNOVA(0xFFC901),
    FLESH(0xFFCBA4),
    SUNGLOW(0xFFCC33),
    GOLDENTAINOI(0xFFCC5C),
    PEACHORANGE(0xFFCC99),
    CHARDONNAY(0xFFCD8C),
    PASTELPINK(0xFFD1DC),
    ROMANTIC(0xFFD2B7),
    GRANDIS(0xFFD38C),
    GOLD(0xFFD700),
    SCHOOLBUSYELLOW(0xFFD800),
    COSMOS(0xFFD8D9),
    MUSTARD(0xFFDB58),
    PEACHSCHNAPPS(0xFFDCD6),
    CARAMEL(0xFFDDAF),
    TUFTBUSH(0xFFDDCD),
    WATUSI(0xFFDDCF),
    PINKLACE(0xFFDDF4),
    NAVAJOWHITE(0xFFDEAD),
    FRANGIPANI(0xFFDEB3),
    PIPPIN(0xFFE1DF),
    PALEROSE(0xFFE1F2),
    NEGRONI(0xFFE2C5),
    CREAMBRULEE(0xFFE5A0),
    PEACH(0xFFE5B4),
    TEQUILA(0xFFE6C7),
    KOURNIKOVA(0xFFE772),
    SANDYBEACH(0xFFEAC8),
    KARRY(0xFFEAD4),
    BROOM(0xFFEC13),
    COLONIALWHITE(0xFFEDBC),
    DERBY(0xFFEED8),
    VISVIS(0xFFEFA1),
    EGGWHITE(0xFFEFC1),
    PAPAYAWHIP(0xFFEFD5),
    FAIRPINK(0xFFEFEC),
    PEACHCREAM(0xFFF0DB),
    LAVENDERBLUSH(0xFFF0F5),
    GORSE(0xFFF14F),
    BUTTERMILK(0xFFF1B5),
    PINKLADY(0xFFF1D8),
    FORGETMENOT(0xFFF1EE),
    TUTU(0xFFF1F9),
    PICASSO(0xFFF39D),
    CHARDON(0xFFF3F1),
    PARISDAISY(0xFFF46E),
    BARLEYWHITE(0xFFF4CE),
    EGGSOUR(0xFFF4DD),
    SAZERAC(0xFFF4E0),
    SERENADE(0xFFF4E8),
    CHABLIS(0xFFF4F3),
    SEASHELLPEACH(0xFFF5EE),
    SAUVIGNON(0xFFF5F3),
    MILKPUNCH(0xFFF6D4),
    VARDEN(0xFFF6DF),
    ROSEWHITE(0xFFF6F5),
    BAJAWHITE(0xFFF8D1),
    GINFIZZ(0xFFF9E2),
    EARLYDAWN(0xFFF9E6),
    LEMONCHIFFON(0xFFFACD),
    BRIDALHEATH(0xFFFAF4),
    SCOTCHMIST(0xFFFBDC),
    SOAPSTONE(0xFFFBF9),
    WITCHHAZE(0xFFFC99),
    BUTTERYWHITE(0xFFFCEA),
    ISLANDSPICE(0xFFFCEE),
    CREAM(0xFFFDD0),
    CHILEANHEATH(0xFFFDE6),
    TRAVERTINE(0xFFFDE8),
    ORCHIDWHITE(0xFFFDF3),
    QUARTERPEARLLUSTA(0xFFFDF4),
    HALFANDHALF(0xFFFEE1),
    APRICOTWHITE(0xFFFEEC),
    RICECAKE(0xFFFEF0),
    BLACKWHITE(0xFFFEF6),
    ROMANCE(0xFFFEFD),
    YELLOW(0xFFFF00),
    LASERLEMON(0xFFFF66),
    PALECANARY(0xFFFF99),
    PORTAFINO(0xFFFFB4),
    IVORY(0xFFFFF0),
    WHITE(0xFFFFFF);

    operator fun invoke(): Color {
        return Color(rgb)
    }

    companion object {
        private val gson = Gson()

        operator fun invoke(name: String): Color {
            val id = name.uppercase().replace("_", "")
            return if (USE_COLORS_FROM_FILE) {
                val arr = gson.fromJson(COLORS_JSON.readText(), Array<Colors>::class.java)
                arr.first { it.name == id }.invoke()
            }
            else entries.first { it.name == id }.invoke()
        }
    }
}