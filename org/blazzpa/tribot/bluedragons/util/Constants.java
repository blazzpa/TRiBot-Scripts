package scripts.org.blazzpa.tribot.bluedragons.util;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Constants {

    public final RSTile centralBankTile = new RSTile(2945, 3370, 0);
    public final RSTile crumbledWallTile = new RSTile(2935, 3355, 0);
    public final RSTile dungeonEntranceTile = new RSTile(2884, 3395, 0);
    public final RSArea faladorBank = new RSArea(new RSTile(2948, 3368, 0), new RSTile(2943, 3373, 0));
    public final int[] bolts = {9142, 9143, 9144}; //mithril addy rune
    public final int[] crossbows = {9183, 9185};   //    addy rune
    public final int[] shields = {1540, 11283, 11284, 11285}; //antifire shield, dragonfire shields
    public final int dragonBones = 536;
    public final int blueDragonhide = 1751;
    public final int[] loot = {dragonBones, blueDragonhide};
    public final int faladorTeletabID = 8009;
    public final int blueDragon1 = 424;
    public final RSTile[] safespot1 = {new RSTile(2900, 9809, 0), new RSTile(2901, 9809, 0)};

}
