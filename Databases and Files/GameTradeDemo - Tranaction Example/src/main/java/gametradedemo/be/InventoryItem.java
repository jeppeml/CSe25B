package gametradedemo.be;

public class InventoryItem {
    private int id;
    private int playerId;
    private String playerName;
    private String itemName;
    private int quantity;

    public InventoryItem(int id, int playerId, String playerName, String itemName, int quantity) {
        this.id = id;
        this.playerId = playerId;
        this.playerName = playerName;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public int getPlayerId() { return playerId; }
    public String getPlayerName() { return playerName; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
}
