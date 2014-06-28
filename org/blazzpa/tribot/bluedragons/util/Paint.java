package scripts.org.blazzpa.tribot.bluedragons.util;

import org.tribot.api2007.Inventory;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

import java.awt.*;

public class Paint {

    private final Font title = new Font("Monotype Corsiva", Font.PLAIN, 25);
    private final Font author = new Font("Monotype Corsiva", Font.PLAIN, 16);
    private final Font info = new Font("Book Antiqua", Font.PLAIN, 15);
    private final Shape bg = new Rectangle(10, 23, 245, 130);
    private final Shape border = new Rectangle(8, 21, 245, 134);
    private final Composite bgComposite = makeComposite(0.5F);
    private final Composite borderComposite = makeComposite(1.0F);
    private final bBlueDragonKiller instance;

    public Paint(final bBlueDragonKiller instance) {
        this.instance = instance;
    }

    private AlphaComposite makeComposite(final float alpha) {
        final int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public void onRepaint(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.DARK_GRAY);
        g2d.setComposite(bgComposite);
        g2d.fill(bg);
        g2d.setColor(Color.BLACK);
        g2d.fill(border);
        g2d.setComposite(borderComposite);

        g2d.setColor(Color.WHITE);

        g2d.setFont(title);
        g2d.drawString("bBDK v" + instance.methods.getVersion(), 12, 43);
        g2d.setFont(author);
        g2d.drawString("By: Blazzpa", 12, 58);

        g2d.setFont(info);
        g2d.drawString("Status: " + instance.getStatus(), 12, 78);
        g2d.drawString("Hides: " + Inventory.getCount(instance.constants.blueDragonhide), 12, 93);
        g2d.drawString("Banked hides: " + instance.getBankedHides(), 12, 108);
        g2d.drawString("Bones: " + Inventory.getCount(instance.constants.dragonBones), 12, 123);
        g2d.drawString("Banked bones: " + instance.getBankedBones(), 12, 138);
        g2d.drawString("Runtime: " + instance.methods.format(System.currentTimeMillis() - instance.getStartTime()), 12, 153);
    }
}
