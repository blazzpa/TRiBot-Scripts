package scripts.org.blazzpa.tribot.bluedragons.util;

public enum FoodEnum {
    LOBSTER(379),
    SWORDFISH(373),
    MONKFISH(7946),
    SHARK(385);

    private int id;

    FoodEnum(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return name();
    }
}
