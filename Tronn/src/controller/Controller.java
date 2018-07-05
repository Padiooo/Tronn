package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.Player;
import model.Wall;
import view.Window;
import view.Pannel;

public class Controller implements Observer {

	private Window window;
	private Model model;
	private boolean alive = true;

	public Controller() {
		setUpMap();
	}

	// setUpMap() create a new model, window and panel,
	// add this as observer of the Keylistener
	// call pan to draw the map grid
	// launch game()
	public void setUpMap() {

		this.model = new Model();
		this.window = new Window(new Pannel(model));
		this.window.getManager().addObserver(this);
		window.getPan().repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Failure launching game at Controller setUpMap()");
			System.exit(0);
		}
		window.getPan().setSetup(false);
		game();
	}

	// game() is the main method
	// call move() method from players
	// check their new position
	// paint their new position and their new wall
	// every 0.5sec
	// untill one player die
	public void game() {

		// shorter calls
		Player one = model.getPlayerOne();
		Player two = model.getPlayerTwo();

		while (alive) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Failure sleep during the game at Controller game()");
				System.exit(0);
			}
			// call move() to change the position of players
			one.move();
			two.move();

			// checking if new positions are in the screen
			if (checkReachingOut(one)) {
				one.crossTheScreen();
			}
			if (checkReachingOut(two)) {
				two.crossTheScreen();
			}

			// checking collision of one with two's wall or two with one's wall
			if ((checkColision(one, two) && checkColision(two, one))
					// if both hit their own walls
					|| (checkColision(one, one) && checkColision(two, two))) {
				// Both died it's a DRAW
				model.updateDraw();
				window.getPan().setEndGame(3);
				alive = false;

				// checking collision of one with two's wall, or one and his own walls
			} else if (checkColision(one, two) || checkColision(one, one)) {
				// one died
				model.updateWinTwo();
				window.getPan().setEndGame(2);
				alive = false;

				// checking collision of two with one's wall, or two and his own walls
			} else if (checkColision(two, one) || checkColision(two, two)) {
				// two died
				model.updateWinOne();
				window.getPan().setEndGame(1);
				alive = false;
			}
			// paint new positions of players and walls
			window.getPan().repaint();
		}

		// sleeping the game so it is playable
		try {
			Thread.sleep(100);
			// refresh the screen
			window.getPan().repaint();
		} catch (InterruptedException e) {
			System.out.println("Failure at the end of a game");
			System.exit(0);
		}
		// atleast one player died, start a new game
		restart();

	}

	// update(Observable o, Object arg) not using her parameters
	// controller is notified from any change on the KeyListener
	// controller gets the key pressed
	// check if it is any correct key used from any player
	// call changeDirection() if it is
	@Override
	public void update(Observable o, Object arg) {

		// get the key pressed as a Char in the KeyListener
		char key = window.getManager().getLetter();

		// keys for player One
		if (key == 'q' || key == 'd') {
			// change the direction of the player one
			if (key == 'q') {
				model.getPlayerOne().changeDirection(-1);
			} else
				model.getPlayerOne().changeDirection(1);
		}
		// keys for player Two
		else if (key == 'k' || key == 'm') {
			// change the direction of the player two
			if (key == 'k') {
				model.getPlayerTwo().changeDirection(-1);
			} else
				model.getPlayerTwo().changeDirection(1);
		}
	}

	// checkColision(Player one, Player two) taking 2 objects of type "Player"
	// checking if the first object collide any wall from the second object return
	// true if it does, false if it doesn't
	public boolean checkColision(Player one, Player two) {

		// shorter calls for "one" position's
		int x = one.getPosX();
		int y = one.getPosY();
		// the ArrayList of wall from "two"
		ArrayList<Wall> wall = two.getWall();
		// checking if "one" position is the same of any wall from "two"
		for (Wall wall2 : wall) {
			if (x == wall2.getPosX() && y == wall2.getPosY()) {
				return true;
			}
		}
		return false;
	}

	// checkReachingOut(Player player) taking 1 object of type "Player",
	// checking if the Player position is out of the map (out of view),
	// return true if it does, false if it doesn't
	public boolean checkReachingOut(Player player) {

		int x = player.getX();
		int y = player.getY();
		if (x < 0 || y < 0 || x > 14 || y > 9) {
			return true;
		}
		return false;
	}

	// restart() called when any player died
	// delete old game stuffs
	// reset alive at true
	// call the method setUpMap() at the end;
	public void restart() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Failure relaoding the game at Controller restart()");
			System.exit(0);
		}
		this.window.getManager().deleteObservers();
		this.window.setVisible(false); // you can't see me!
		this.window.dispose(); // Destroy the JFrame object
		this.window = null;
		this.model = null;
		alive = true;

		setUpMap();
	}

	/************ Normal Getters and Setters ************/

	public Window getWindow() {
		return window;
	}

	public Model getModel() {
		return model;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
