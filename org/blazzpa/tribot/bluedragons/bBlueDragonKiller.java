package org.blazzpa.tribot.bluedragons;

import org.blazzpa.tribot.bluedragons.actions.Task;
import org.blazzpa.tribot.bluedragons.actions.impl.*;
import org.blazzpa.tribot.bluedragons.util.Constants;
import org.blazzpa.tribot.bluedragons.util.Methods;
import org.blazzpa.tribot.bluedragons.util.Settings;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSItem;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

import java.util.Arrays;

@ScriptManifest(authors = {"Blazzpa"}, name = "bBlueDragonKiller", category = "Moneymaking", description = "Kills blue" +
        " dragons using safespots in the Taverly dungeon. Uses the shortcut so you must have at least 70 agility.")
public class bBlueDragonKiller extends Script {

    private boolean completedStartup = false;
    private boolean terminate = false;

    private Task[] tasks = new Task[]{new Bank(this), new Teleport(this), new WalkToBank(this),
            new ClimbCrumbledWall(this), new EnterDungeon(this), new EnterPipe(this), new WalkToDungeonLadder(this),
            new WalkToCrumbledWall(this)};
    private Constants constants = new Constants();
    private Settings settings = new Settings();
    private Methods methods = new Methods(this);

    @Override
    public void run() {
        while (!terminate) {
            if (!completedStartup) {
                if (!startupCheck()) {
                    terminate = true;
                } else {
                    completedStartup = false;
                }
            } else {
                for (Task task : tasks) {
                    if (task.validate()) {
                        task.execute();
                    }
                }
            }
        }
    }

    private boolean startupCheck() {
        final RSItem bolts = Equipment.getItem(Equipment.SLOTS.ARROW);
        final RSItem shield = Equipment.getItem(Equipment.SLOTS.SHIELD);
        final RSItem crossbow = Equipment.getItem(Equipment.SLOTS.WEAPON);
        Arrays.sort(constants.bolts);
        Arrays.sort(constants.shields);
        Arrays.sort(constants.crossbows);
        return Skills.getCurrentLevel(Skills.SKILLS.AGILITY) >= 70 && constants.faladorBank.contains(Player.getPosition()) &&
                Arrays.binarySearch(constants.bolts, bolts.getID()) >= 0 && bolts.getStack() >= settings.minimumBoltsAmount &&
                Arrays.binarySearch(constants.shields, shield.getID()) >= 0 &&
                Arrays.binarySearch(constants.crossbows, crossbow.getID()) >= 0;
    }

    public boolean hasCompletedStartup() {
        return completedStartup;
    }

    public Constants getConstants() {
        return constants;
    }

    public Settings getSettings() {
        return settings;
    }

    public Methods getMethods() {
        return methods;
    }
}
