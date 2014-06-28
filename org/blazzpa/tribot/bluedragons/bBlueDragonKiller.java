package scripts.org.blazzpa.tribot.bluedragons;

import org.tribot.api2007.Equipment;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSItem;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.actions.impl.*;
import scripts.org.blazzpa.tribot.bluedragons.util.*;
import scripts.org.blazzpa.tribot.bluedragons.util.Paint;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

@ScriptManifest(authors = {"Blazzpa"}, name = "bBlueDragonKiller", category = "Moneymaking", version = 1.2,
        description = "Kills blue dragons using safespots in the Taverly dungeon. <br>" +
                " Uses the shortcut so you must have at least 70 agility.<br>" +
                "<br><b>Version 1.2:</b>" +
                "<br><ul>" +
                "<li>Ranging potion support - have in inventory or bank</li>" +
                "</ul><br>")
public class bBlueDragonKiller extends Script implements Painting {

    public final Constants constants = new Constants();
    public final Settings settings = new Settings();
    public final Methods methods = new Methods(this);
    public boolean terminate = false;
    private final Task[] tasks = new Task[]{new CameraManager(this), new Heal(this), new Bank(this),
            new Teleport(this), new WalkToBank(this), new ClimbCrumbledWall(this), new EnterDungeon(this),
            new EnterPipe(this), new WalkToDungeonLadder(this), new WalkToCrumbledWall(this), new Loot(this),
            new AttackDragon(this), new DrinkPotion(this)};
    private final Paint paint = new Paint(this);
    private boolean completedStartup = false;
    private String status = "Startup";
    private int bankedBones = 0;
    private int bankedHides = 0;
    private long startTime;
    private boolean guiDone;

    @Override
    public void run() {
        while (!terminate) {
            if (guiDone) {
                if (!completedStartup) {
                    startTime = System.currentTimeMillis();
                    if (!startupCheck()) {
                        println("Please start in the bank and have a crossbow, bolts and a antifire shield equipped.");
                        terminate = true;
                    } else {
                        completedStartup = true;
                    }
                } else {
                    for (Task task : tasks) {
                        println("Checking task: " + task.getClass().getSimpleName());
                        if (task.validate()) {
                            status = task.getClass().getSimpleName();
                            task.execute();
                        }
                        sleep(20, 50);
                    }
                }
            } else {
                final GUI g = new GUI(this);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        g.setVisible(true);
                    }
                });
                while (!guiDone) {
                    sleep(20, 50);
                }
            }
        }
        println("The script has shutdown due to an error. Please try again.");
        println("If the script shut down on startup, make sure to input correct values in the GUI.");
    }

    private boolean startupCheck() {
        final RSItem bolts = Equipment.getItem(Equipment.SLOTS.ARROW);
        final RSItem shield = Equipment.getItem(Equipment.SLOTS.SHIELD);
        final RSItem crossbow = Equipment.getItem(Equipment.SLOTS.WEAPON);
        Arrays.sort(constants.bolts);
        Arrays.sort(constants.shields);
        Arrays.sort(constants.crossbows);
        return Skills.getCurrentLevel(Skills.SKILLS.AGILITY) >= 70 &&
                (constants.faladorBank.contains(Player.getPosition()) || methods.isBankingComplete()) &&
                Arrays.binarySearch(constants.bolts, bolts.getID()) >= 0 && bolts.getStack() >=
                settings.minimumBoltsAmount && Arrays.binarySearch(constants.shields, shield.getID()) >= 0 &&
                Arrays.binarySearch(constants.crossbows, crossbow.getID()) >= 0;
    }

    public String getStatus() {
        return status;
    }

    public void print(Object o) {
        println(o);
    }

    public void addBankedBones(int amount) {
        bankedBones += amount;
    }

    public void addBankedHides(int amount) {
        bankedHides += amount;
    }

    public int getBankedBones() {
        return bankedBones;
    }

    public int getBankedHides() {
        return bankedHides;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setGUIDone(final boolean done) {
        guiDone = done;
    }

    @Override
    public void onPaint(Graphics graphics) {
        paint.onRepaint(graphics);
    }


}
