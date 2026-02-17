package gametradedemo.dal;

import gametradedemo.be.InventoryItem;
import gametradedemo.be.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DALManager {
    private final ConnectionManager cm;

    public DALManager() {
        cm = new ConnectionManager();
    }

    public Connection getConnection() throws SQLException {
        return cm.getConnection();
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Player");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                players.add(new Player(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getInt("Gold")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    public List<InventoryItem> getAllInventory() {
        List<InventoryItem> items = new ArrayList<>();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT i.Id, i.PlayerId, p.Name AS PlayerName, i.ItemName, i.Quantity " +
                    "FROM Inventory i JOIN Player p ON i.PlayerId = p.Id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(new InventoryItem(
                        rs.getInt("Id"),
                        rs.getInt("PlayerId"),
                        rs.getString("PlayerName"),
                        rs.getString("ItemName"),
                        rs.getInt("Quantity")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    // --- Individual trade steps (for step-by-step demo) ---

    public void stepRemoveItem(Connection con, int playerId, String itemName) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM Inventory WHERE PlayerId = ? AND ItemName = ?");
        ps.setInt(1, playerId);
        ps.setString(2, itemName);
        ps.executeUpdate();
    }

    public void stepGiveItem(Connection con, int playerId, String itemName) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (?, ?, 1)");
        ps.setInt(1, playerId);
        ps.setString(2, itemName);
        ps.executeUpdate();
    }

    public void stepDeductGold(Connection con, int playerId, int amount) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "UPDATE Player SET Gold = Gold - ? WHERE Id = ?");
        ps.setInt(1, amount);
        ps.setInt(2, playerId);
        ps.executeUpdate();
    }

    public void stepAddGold(Connection con, int playerId, int amount) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "UPDATE Player SET Gold = Gold + ? WHERE Id = ?");
        ps.setInt(1, amount);
        ps.setInt(2, playerId);
        ps.executeUpdate();
    }

    public void resetDatabase() {
        try (Connection con = cm.getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM Inventory");
            stmt.executeUpdate("DELETE FROM Player");
            stmt.executeUpdate("DBCC CHECKIDENT ('Player', RESEED, 0)");
            stmt.executeUpdate("DBCC CHECKIDENT ('Inventory', RESEED, 0)");
            stmt.executeUpdate("INSERT INTO Player (Name, Gold) VALUES ('Alice', 100)");
            stmt.executeUpdate("INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (1, 'Legendary Sword', 1)");
            stmt.executeUpdate("INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (1, 'Health Potion', 5)");
            stmt.executeUpdate("INSERT INTO Player (Name, Gold) VALUES ('Bob', 800)");
            stmt.executeUpdate("INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (2, 'Iron Shield', 1)");
            stmt.executeUpdate("INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (2, 'Health Potion', 3)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void batchInsertItems(int playerId, String[][] items) throws SQLException {
        try (Connection con = cm.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (?, ?, ?)");
            for (String[] item : items) {
                ps.setInt(1, playerId);
                ps.setString(2, item[0]);
                ps.setInt(3, Integer.parseInt(item[1]));
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
        }
    }
}
