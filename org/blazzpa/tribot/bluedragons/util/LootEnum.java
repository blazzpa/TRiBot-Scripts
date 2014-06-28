package scripts.org.blazzpa.tribot.bluedragons.util;

public enum LootEnum {
    NATURE_RUNE(561),
    LAW_RUNE(563),
    LOOP_HALF_OF_KEY(0), //TODO
    TOOTH_HALF_OF_KEY(0), //TODO
    RUNITE_BAR(2363),
    RUNE_SPEAR(1247),
    RUNE_BATTLEAXE(1373),
    RUNE_2H_SWORD(1319),
    UNCUT_DIAMOND(1617),
    SILVER_ORE(443),
    RUNE_SQ_SHIELD(1185),
    RUNE_ARROW(892),
    DEATH_RUNE(560),
    UNCUT_DRAGONSTONE(1631),
    DRAGONSTONE(1615),
    RUNE_KITESHIELD(1201),
    DRAGON_MED_HELM(1149),
    SHIELD_LEFT_HALF(2366),
    DRAGON_SPEAR(1249),
    RUNE_DAGGER(1213),
    BLUE_DRAGONHIDE(1751),
    DRAGON_BONES(536);

    private int id;

    LootEnum(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
