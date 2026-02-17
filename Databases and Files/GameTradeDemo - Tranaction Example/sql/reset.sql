-- Reset the database to initial state between demos
USE CS2025b_e_31_GameTradeDemo;
GO

DELETE FROM Inventory;
DELETE FROM Player;

DBCC CHECKIDENT ('Player', RESEED, 0);
DBCC CHECKIDENT ('Inventory', RESEED, 0);

INSERT INTO Player (Name, Gold) VALUES ('Alice', 100);
INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (1, 'Legendary Sword', 1);
INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (1, 'Health Potion', 5);

INSERT INTO Player (Name, Gold) VALUES ('Bob', 800);
INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (2, 'Iron Shield', 1);
INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (2, 'Health Potion', 3);

SELECT * FROM Player;
SELECT * FROM Inventory;
