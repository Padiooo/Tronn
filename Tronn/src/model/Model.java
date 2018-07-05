package model;

import model.dao.ScoreDAO;
import model.dao.TronnJDBC;

public class Model {

	private Player playerOne;
	private Player playerTwo;

	private String colorOne = "red";
	private String colorTwo = "pink";

	private SpriteSheet spriteSheet;

	public Model() {
		// create a SpriteSheet
		this.spriteSheet = new SpriteSheet();
		// connect to the database for the first time
		TronnJDBC conn = TronnJDBC.getInstance();
		// create 2 Player
		this.playerOne = new Player(spriteSheet, colorOne, 1, new ScoreDAO(conn.colorScore(colorOne)));
		this.playerTwo = new Player(spriteSheet, colorTwo, 2, new ScoreDAO(conn.colorScore(colorTwo)));
	}

	// updateWinOne() called by the controller at the end
	// of a game, will increment the number of win of the
	// playerOne, and increment number of lose of playerTwo
	public void updateWinOne() {
		TronnJDBC conn = TronnJDBC.getInstance();
		conn.updateWinColor(colorOne);
		conn.updateLoseColor(colorTwo);
	}

	// updateWinTwo() called by the controller at the end
	// of a game, will increment the number of win of the
	// playerTwo, and increment number of lose of playerOne
	public void updateWinTwo() {
		TronnJDBC conn = TronnJDBC.getInstance();
		conn.updateWinColor(colorTwo);
		conn.updateLoseColor(colorOne);
	}

	// updateDraw() called by the controller at the end
	// of a game, will increment the number of draw of the
	// playerOne and playerTwo
	public void updateDraw() {
		TronnJDBC conn = TronnJDBC.getInstance();
		conn.updateDraw(colorOne, colorTwo);
	}

	/************ Normal Getters and Setters ************/

	public Player getPlayerOne() {
		return playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

}
