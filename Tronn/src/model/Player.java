package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.dao.ScoreDAO;

public class Player {
	
	// used to move player in the grid with UA
	private int x;	
	private int y;
	// used to map between grid UA and real grid (real grid = UA * size)
	private int size = 40;
	// used by the Pannel to display the image in the real grid
	private int posX;
	private int posY;

	private String color;
	private BufferedImage[] imgTab = new BufferedImage[8];
	private BufferedImage imgPlayer = null;

	private EnumDirection[] directionTab = EnumDirection.values();
	private EnumDirection direction;
	private int currentDirection;

	private ArrayList<Wall> wall = new ArrayList<>();

	private ScoreDAO score;

	public Player(SpriteSheet spriteSheet, String color, int number, ScoreDAO score) {

		this.color = color;
		if (score != null) {
			this.score = score;
		}

		// setting where they start and where they look at
		if (number == 1) {
			this.x = 1;
			this.y = 7;
			this.currentDirection = 1;
		} else if (number == 2) {
			this.x = 13;
			this.y = 2;
			this.currentDirection = 3;
		}
		// setting the row to get images matching the color
		int j = 0;
		if (color == "blue") {
			j = 0;
		} else if (color == "red") {
			j = 1;
		} else if (color == "green") {
			j = 2;
		} else if (color == "pink") {
			j = 3;
		} else if (color == "magenta") {
			j = 4;
		} else if (color == "yellow") {
			j = 5;
		} else if (color == "black") {
			j = 6;
		}

		setDirection();
		// getting images
		if (spriteSheet != null) {
			for (int i = 0; i < imgTab.length; i++) {
				this.imgTab[i] = spriteSheet.crop(i, j, size);
			}
			setImgPlayer();
		}

		setPosX();
		setPosY();

	}

	// changeDirection(int key) change the direction
	// by adding the key received to the integer "currentDirection"
	// and switch the direction
	// if new direction > 3 set it to 0
	// if new direction < 0 set it to 3
	public void changeDirection(int key) {

		switch (key) {
		case 1:
			currentDirection += key;
			if (currentDirection > 3)
				currentDirection = 0;
			break;
		case -1:
			currentDirection += key;
			if (currentDirection < 0)
				currentDirection = 3;
			break;
		default:
			break;
		}
		setDirection();
		setImgPlayer();
	}

	// move() 
	// first create a new object " Wall " 
	// and give his current position (x,y) then
	// change player x or y by + or - 1
	// only x or only y changed
	
	public void move() {

		wall.add(new Wall(posX, posY, imgTab[4]));
		switch (direction) {
		case Up:
			y--;
			setPosY();
			break;
		case Right:
			x++;
			setPosX();
			break;
		case Down:
			y++;
			setPosY();
			break;
		case Left:
			x--;
			setPosX();
			break;
		default:
			break;
		}
	}

	// crossTheScreen() called by the controller
	// use to cross the direction
	// if player is moving out of the screen on the left
	// he appears on the right etc
	
	public void crossTheScreen() {

		switch (direction) {
		case Up:
			y = 9;
			setPosY();
			break;
		case Right:
			x = 0;
			setPosX();
			break;
		case Down:
			y = 0;
			setPosY();
			break;
		case Left:
			x = 14;
			setPosX();
			break;
		default:
			break;
		}
	}

	// setDirection() called after any change on " currentDirection "
	// to refresh the direction
	public void setDirection() {
		this.direction = directionTab[currentDirection];
	}

	// setDirection() called after any change on " currentDirection "
	// to refresh the image
	public void setImgPlayer() {
		this.imgPlayer = imgTab[currentDirection];
	}

	// setPosX() called after any change on " x "
	// to refresh the posX used by the Pannel
	public void setPosX() {
		this.posX = x * size;
	}

	// setPosX() called after any change on " y "
	// to refresh the posY used by the Pannel
	public void setPosY() {
		this.posY = y * size;
	}

	// getImg(int i) used by the Pannel to display 
	// Win / Draw in the same color of players
	public BufferedImage getImg(int i) {
		return imgTab[i];
	}

	/************ Normal Getters and Setters ************/

	public BufferedImage[] getImgTab() {
		return imgTab;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public BufferedImage getImgPlayer() {
		return imgPlayer;
	}

	public void setImgTab(BufferedImage[] imgTab) {
		this.imgTab = imgTab;
	}

	public void setDirectionTab(EnumDirection[] directionTab) {
		this.directionTab = directionTab;
	}

	public void setDirection(EnumDirection direction) {
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList<Wall> getWall() {
		return wall;
	}

	public void setWall(ArrayList<Wall> wall) {
		this.wall = wall;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public EnumDirection[] getDirectionTab() {
		return directionTab;
	}

	public EnumDirection getDirection() {
		return direction;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

	public ScoreDAO getScore() {
		return score;
	}

	public void setScore(ScoreDAO score) {
		this.score = score;
	}
}
