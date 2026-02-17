package gametradedemo.gui;

import gametradedemo.be.InventoryItem;
import gametradedemo.be.Player;
import gametradedemo.bll.BLLManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML private TableView<Player> tablePlayers;
    @FXML private TableColumn<Player, String> columnPlayerName;
    @FXML private TableColumn<Player, Integer> columnGold;

    @FXML private TableView<InventoryItem> tableInventory;
    @FXML private TableColumn<InventoryItem, String> columnOwner;
    @FXML private TableColumn<InventoryItem, String> columnItem;
    @FXML private TableColumn<InventoryItem, Integer> columnQuantity;

    @FXML private TextArea txtLog;
    @FXML private CheckBox chkCrash;
    @FXML private HBox buttonBar;

    private final BLLManager bllManager = BLLManager.getInstance();

    private static final int PAUSE_MS = 1500;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnPlayerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnGold.setCellValueFactory(new PropertyValueFactory<>("gold"));

        columnOwner.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        columnItem.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        refreshTables();
        log("Ready. Alice has a Legendary Sword (100 gold). Bob has 800 gold.");
        log("The trade: Alice's Legendary Sword for 500 of Bob's gold.\n");
    }

    // ==================== WITHOUT TRANSACTION ====================

    @FXML
    private void onTradeWithoutTransaction(ActionEvent event) {
        buttonBar.setDisable(true);
        txtLog.clear();
        log("=== Trade WITHOUT Transaction (auto-commit ON) ===");
        log("Each SQL statement is saved to the database IMMEDIATELY.\n");

        boolean crashEnabled = chkCrash.isSelected();

        Thread thread = new Thread(() -> {
            try {
                // Step 1
                logLater("Step 1/4: DELETE Legendary Sword from Alice...");
                pause();
                try (Connection con = bllManager.getConnection()) {
                    bllManager.stepRemoveItem(con, 1, "Legendary Sword");
                }
                logLater("         Executed and AUTO-COMMITTED!");
                refreshLater();
                pause();
                logLater("  >> Check tables - Alice's sword is GONE!\n");
                pause();

                // Step 2
                logLater("Step 2/4: INSERT Legendary Sword for Bob...");
                pause();
                try (Connection con = bllManager.getConnection()) {
                    bllManager.stepGiveItem(con, 2, "Legendary Sword");
                }
                logLater("         Executed and AUTO-COMMITTED!");
                refreshLater();
                pause();
                logLater("  >> Check tables - Bob now has the sword!\n");
                pause();

                // Crash?
                if (crashEnabled) {
                    logLater("==========================================");
                    logLater("   CRASH! SERVER DOWN! CONNECTION LOST!");
                    logLater("==========================================");
                    logLater("Steps 3 and 4 were NEVER executed!\n");
                    pause();
                    logLater("RESULT:");
                    logLater("  Alice: lost sword, got NO gold (still 100)");
                    logLater("  Bob:   got sword, paid NOTHING (still 800)");
                    logLater("");
                    logLater(">>> DATABASE IS INCONSISTENT! <<<");
                    logLater("Bob got a free Legendary Sword!");
                    return;
                }

                // Step 3
                logLater("Step 3/4: UPDATE Bob's gold -500...");
                pause();
                try (Connection con = bllManager.getConnection()) {
                    bllManager.stepDeductGold(con, 2, 500);
                }
                logLater("         Executed and AUTO-COMMITTED!");
                refreshLater();
                pause();

                // Step 4
                logLater("Step 4/4: UPDATE Alice's gold +500...");
                pause();
                try (Connection con = bllManager.getConnection()) {
                    bllManager.stepAddGold(con, 1, 500);
                }
                logLater("         Executed and AUTO-COMMITTED!");
                refreshLater();
                pause();

                logLater("\nTrade complete! All 4 steps ran successfully.");
                logLater("This worked... but only because nothing went wrong.");

            } catch (Exception e) {
                logLater("ERROR: " + e.getMessage());
            } finally {
                Platform.runLater(() -> buttonBar.setDisable(false));
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    // ==================== WITH TRANSACTION ====================

    @FXML
    private void onTradeWithTransaction(ActionEvent event) {
        buttonBar.setDisable(true);
        txtLog.clear();
        log("=== Trade WITH Transaction (auto-commit OFF) ===");
        log("Nothing is saved until we call commit().\n");

        boolean crashEnabled = chkCrash.isSelected();

        Thread thread = new Thread(() -> {
            Connection con = null;
            try {
                con = bllManager.getConnection();
                con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                con.setAutoCommit(false);
                logLater("con.setAutoCommit(false);  // BEGIN TRANSACTION\n");
                pause();

                // Step 1
                logLater("Step 1/4: DELETE Legendary Sword from Alice...");
                pause();
                bllManager.stepRemoveItem(con, 1, "Legendary Sword");
                logLater("         Executed (NOT committed yet!)");
                refreshLater();
                pause();
                logLater("  >> Check tables - NOTHING changed!\n");
                pause();

                // Step 2
                logLater("Step 2/4: INSERT Legendary Sword for Bob...");
                pause();
                bllManager.stepGiveItem(con, 2, "Legendary Sword");
                logLater("         Executed (NOT committed yet!)");
                refreshLater();
                pause();
                logLater("  >> Tables STILL unchanged! Data is pending in transaction.\n");
                pause();

                // Crash?
                if (crashEnabled) {
                    logLater("==========================================");
                    logLater("   CRASH! SERVER DOWN! CONNECTION LOST!");
                    logLater("==========================================\n");
                    pause();
                    logLater("con.rollback();  // Undo ALL pending changes");
                    con.rollback();
                    logLater("Rollback complete!\n");
                    refreshLater();
                    pause();
                    logLater("RESULT:");
                    logLater("  Alice: still has sword + 100 gold");
                    logLater("  Bob:   still has 800 gold, no sword");
                    logLater("");
                    logLater(">>> DATABASE IS STILL CONSISTENT! <<<");
                    logLater("The transaction protected the data!");
                    return;
                }

                // Step 3
                logLater("Step 3/4: UPDATE Bob's gold -500...");
                pause();
                bllManager.stepDeductGold(con, 2, 500);
                logLater("         Executed (NOT committed yet!)");
                refreshLater();
                pause();
                logLater("  >> Tables STILL unchanged!\n");
                pause();

                // Step 4
                logLater("Step 4/4: UPDATE Alice's gold +500...");
                pause();
                bllManager.stepAddGold(con, 1, 500);
                logLater("         Executed (NOT committed yet!)");
                refreshLater();
                pause();
                logLater("  >> Tables STILL unchanged! All 4 steps pending.\n");
                pause();

                // Commit
                logLater("con.commit();  // Save ALL changes at once!");
                con.commit();
                logLater("COMMIT successful!\n");
                refreshLater();
                pause();
                logLater("  >> NOW the tables update! All 4 changes applied at once.");
                logLater("Trade complete! Transaction guaranteed all-or-nothing.");

            } catch (Exception e) {
                logLater("ERROR: " + e.getMessage());
                if (con != null) {
                    try { con.rollback(); } catch (SQLException ex) { /* ignore */ }
                }
            } finally {
                if (con != null) {
                    try { con.close(); } catch (SQLException e) { /* ignore */ }
                }
                Platform.runLater(() -> buttonBar.setDisable(false));
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    // ==================== BATCH + RESET ====================

    @FXML
    private void onBatchInsert(ActionEvent event) {
        log("\n--- Batch insert: Starter Pack for Alice ---");
        String[][] starterPack = {
                {"Mana Potion", "5"},
                {"Fire Scroll", "3"},
                {"Iron Helmet", "1"}
        };
        try {
            bllManager.batchInsertItems(1, starterPack);
            log("Batch complete! 3 item types inserted in one go.");
        } catch (SQLException e) {
            log("Batch failed: " + e.getMessage());
        }
        refreshTables();
    }

    @FXML
    private void onReset(ActionEvent event) {
        bllManager.resetDatabase();
        refreshTables();
        txtLog.clear();
        log("Database reset to initial state.");
        log("Ready. Alice has a Legendary Sword (100 gold). Bob has 800 gold.");
        log("The trade: Alice's Legendary Sword for 500 of Bob's gold.\n");
    }

    // ==================== HELPERS ====================

    private void refreshTables() {
        ObservableList<Player> players = FXCollections.observableArrayList(bllManager.getAllPlayers());
        tablePlayers.setItems(players);

        ObservableList<InventoryItem> items = FXCollections.observableArrayList(bllManager.getAllInventory());
        tableInventory.setItems(items);
    }

    private void refreshLater() {
        Platform.runLater(this::refreshTables);
    }

    private void log(String message) {
        txtLog.appendText(message + "\n");
    }

    private void logLater(String message) {
        Platform.runLater(() -> txtLog.appendText(message + "\n"));
    }

    private void pause() {
        try { Thread.sleep(PAUSE_MS); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
