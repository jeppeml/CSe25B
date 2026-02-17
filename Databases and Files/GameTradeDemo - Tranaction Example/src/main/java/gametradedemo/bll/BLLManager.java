package gametradedemo.bll;

import gametradedemo.be.InventoryItem;
import gametradedemo.be.Player;
import gametradedemo.dal.DALManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BLLManager {
    private static BLLManager instance;
    private final DALManager dalManager;

    public static BLLManager getInstance() {
        if (instance == null) {
            instance = new BLLManager();
        }
        return instance;
    }

    private BLLManager() {
        dalManager = new DALManager();
    }

    public Connection getConnection() throws SQLException {
        return dalManager.getConnection();
    }

    public List<Player> getAllPlayers() {
        return dalManager.getAllPlayers();
    }

    public List<InventoryItem> getAllInventory() {
        return dalManager.getAllInventory();
    }

    public void stepRemoveItem(Connection con, int playerId, String itemName) throws SQLException {
        dalManager.stepRemoveItem(con, playerId, itemName);
    }

    public void stepGiveItem(Connection con, int playerId, String itemName) throws SQLException {
        dalManager.stepGiveItem(con, playerId, itemName);
    }

    public void stepDeductGold(Connection con, int playerId, int amount) throws SQLException {
        dalManager.stepDeductGold(con, playerId, amount);
    }

    public void stepAddGold(Connection con, int playerId, int amount) throws SQLException {
        dalManager.stepAddGold(con, playerId, amount);
    }

    public void resetDatabase() {
        dalManager.resetDatabase();
    }

    public void batchInsertItems(int playerId, String[][] items) throws SQLException {
        dalManager.batchInsertItems(playerId, items);
    }
}
