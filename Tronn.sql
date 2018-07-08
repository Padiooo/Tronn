
CREATE DATABASE IF NOT EXISTS Tronn;
USE Tronn;

DROP TABLE IF EXISTS Score;
CREATE TABLE IF NOT EXISTS Score (
	
    ID VARCHAR(10) PRIMARY KEY NOT NULL,
    Win INT,
    Draw INT,
    Lose INT,
    LastUpdate DATETIME
);

INSERT INTO Score (ID, Win, Draw, Lose, LastUpdate) VALUES ('red', 0, 0, 0, NOW());
INSERT INTO Score (ID, Win, Draw, Lose, LastUpdate) VALUES ('blue', 0, 0, 0, NOW());
INSERT INTO Score (ID, Win, Draw, Lose, LastUpdate) VALUES ('yellow', 0, 0, 0, NOW());
INSERT INTO Score (ID, Win, Draw, Lose, LastUpdate) VALUES ('magenta', 0, 0, 0, NOW());
INSERT INTO Score (ID, Win, Draw, Lose, LastUpdate) VALUES ('pink', 0, 0, 0, NOW());
INSERT INTO Score (ID, Win, Draw, Lose, LastUpdate) VALUES ('green', 0, 0, 0, NOW());
INSERT INTO Score (ID, Win, Draw, Lose, LastUpdate) VALUES ('black', 0, 0, 0, NOW());

DROP PROCEDURE IF EXISTS UpdateWin;
DROP PROCEDURE IF EXISTS UpdateLose;
DROP PROCEDURE IF EXISTS UpdateDraw;
DROP PROCEDURE IF EXISTS SelectColor;

DELIMITER //

CREATE PROCEDURE UpdateWin(IN color VARCHAR(10))
BEGIN 
	SET SQL_SAFE_UPDATES = 0;
	UPDATE Score
	SET Win = Win+1, LastUpdate = NOW()
	WHERE ID = color;
END //

CREATE PROCEDURE UpdateLose(IN color VARCHAR(10))
BEGIN 
	SET SQL_SAFE_UPDATES = 0;
	UPDATE Score
	SET Lose = Lose+1, LastUpdate = NOW()
	WHERE ID = color;
END //

CREATE PROCEDURE UpdateDraw(IN color1 VARCHAR(10), IN color2 VARCHAR(10))
BEGIN 
	SET SQL_SAFE_UPDATES = 0;
	UPDATE Score
	SET Draw = Draw+1, LastUpdate = NOW()
	WHERE ID = color1 OR ID = color2;
END //

CREATE PROCEDURE SelectColor(IN color VARCHAR(10))
BEGIN 
	SELECT * FROM Score WHERE ID = color;
END //

DELIMITER ;
