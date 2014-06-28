package scripts.org.blazzpa.tribot.bluedragons.util;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSCharacter;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.ScriptManifest;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

import java.util.ArrayList;
import java.util.Random;

public class Methods {

    private final Random random = new Random(System.currentTimeMillis());
    private final bBlueDragonKiller instance;

    public Methods(final bBlueDragonKiller instance) {
        this.instance = instance;
    }

    public boolean isBankingComplete() {
        return Inventory.getCount(instance.settings.selectedFoodID) >= instance.settings.minimumFoodCount
                && Inventory.getCount(instance.constants.faladorTeletabID) > 0;
    }

    public boolean isInSafespot(final RSTile[] safeTiles) {
        for (final RSTile tile : safeTiles) {
            if (Player.getPosition().equals(tile)) {
                return true;
            }
        }
        return false;
    }

    public RSTile getRandomSafespotTile(final RSTile[] safeTiles) {
        return safeTiles[random.nextInt(safeTiles.length)];
    }

    public boolean isPlayerInteracting() {
        return Player.getRSPlayer().getInteractingCharacter() != null;
    }

    /**
     * TODO: Script will be bugged if you level up while attacking it thinks you are in combat
     */

    public boolean isInteractingWithCorrectDragon() {
        if (isPlayerInteracting()) {
            final RSCharacter interactingCharacter = Player.getRSPlayer().getInteractingCharacter();
            if (interactingCharacter instanceof RSNPC) {
                final RSNPC npc = (RSNPC) interactingCharacter;
                return npc.getID() == instance.constants.blueDragon1;
            }
        }
        return false;
    }

    public boolean inDungeon() {
        return Player.getPosition().getY() > 9000;
    }

    public boolean inDragonArea() {
        return inDungeon() && Player.getPosition().getX() > 2887;
    }

    public boolean pastCrumbledWall() {
        return Player.getPosition().getX() <= 2934;
    }

    public boolean hasEnoughHealth() {
        return Skills.getCurrentLevel(Skills.SKILLS.HITPOINTS) > instance.settings.minimumHitpoints;
    }

    public boolean isTeleporting() {
        final int animation = Player.getAnimation();
        return animation == 4069 || animation == 4071;
    }

    public int nextInt(final int min, final int max) {
        return random.nextInt(max - min) + min;
    }

    public String format(final long time) {
        final StringBuilder t = new StringBuilder();
        final long total_secs = time / 1000;
        final long total_mins = total_secs / 60;
        final long total_hrs = total_mins / 60;
        final long total_days = total_hrs / 24;
        final int secs = (int) total_secs % 60;
        final int mins = (int) total_mins % 60;
        final int hrs = (int) total_hrs % 24;
        final int days = (int) total_days;
        if (days > 0) {
            if (days < 10) {
                t.append("0");
            }
            t.append(days);
            t.append(":");
        }
        if (hrs < 10) {
            t.append("0");
        }
        t.append(hrs);
        t.append(":");
        if (mins < 10) {
            t.append("0");
        }
        t.append(mins);
        t.append(":");
        if (secs < 10) {
            t.append("0");
        }
        t.append(secs);
        return t.toString();
    }

    public int calculatePottedRangeLevel() {
        final int rangeLevel = Skills.getActualLevel(Skills.SKILLS.RANGED);
        return rangeLevel + 4 + (int) Math.floor(rangeLevel / 10);
    }

    public double getVersion() {
        return instance.getClass().getAnnotation(ScriptManifest.class).version();
    }

    public int[] getLootIDArray(final ArrayList<String> items) {
        int[] ids = new int[items.size()];
        for (int i = 0; i < ids.length; i++) {
            for (final LootEnum l : LootEnum.values()) {
                if (l.name().equals(items.get(i))) {
                    ids[i] = l.getID();
                }
            }
        }
        return ids;
    }

    public int getFoodId(final String item) {
        for (FoodEnum fe : FoodEnum.values()) {
            if (fe.name().equals(item)) {
                return fe.getID();
            }
        }
        return -1;
    }

}
