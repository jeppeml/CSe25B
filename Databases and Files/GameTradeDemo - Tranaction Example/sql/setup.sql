-- Game Trade Demo: Setup Script
-- Run this in SQL Server Management Studio before the lesson

CREATE DATABASE CS2025b_e_31_GameTradeDemo;
GO
USE CS2025b_e_31_GameTradeDemo;
GO

CREATE TABLE Player (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(50) NOT NULL,
    Gold INT NOT NULL DEFAULT 0
);

CREATE TABLE Inventory (
    Id INT PRIMARY KEY IDENTITY(1,1),
    PlayerId INT NOT NULL FOREIGN KEY REFERENCES Player(Id),
    ItemName NVARCHAR(100) NOT NULL,
    Quantity INT NOT NULL DEFAULT 1
);

-- Player A (Alice): has a Legendary Sword, 100 gold
INSERT INTO Player (Name, Gold) VALUES ('Alice', 100);
INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (1, 'Legendary Sword', 1);
INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (1, 'Health Potion', 5);

-- Player B (Bob): no sword, 800 gold
INSERT INTO Player (Name, Gold) VALUES ('Bob', 800);
INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (2, 'Iron Shield', 1);
INSERT INTO Inventory (PlayerId, ItemName, Quantity) VALUES (2, 'Health Potion', 3);

-- Verify setup
SELECT * FROM Player;
SELECT * FROM Inventory;
