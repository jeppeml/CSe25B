package gametradedemo.be;

public class Player {
    private int id;
    private String name;
    private int gold;

    public Player(int id, String name, int gold) {
        this.id = id;
        this.name = name;
        this.gold = gold;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getGold() { return gold; }
}
