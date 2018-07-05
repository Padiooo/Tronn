package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Model;
import model.Player;
import model.Wall;

public class Pannel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Model model; // a duplication of the same Model that the controller is changing stuffs

	private boolean setup = true; // to display rows and columns at the beginning
	private int endGame; // to display WIN or DRAW

	public Pannel(Model model) {
		this.model = model;
	}

	public void paintComponent(Graphics g) {

		// shorter calls
		Player one = model.getPlayerOne();
		Player two = model.getPlayerTwo();

		if (setup) { // setting the Map at it's beginning

			g.clearRect(0, 0, getWidth(), getHeight());

			// display the grid at the beginning

			for (int i = 0; i < 6; i++) {
				// vertical
				g.drawLine(i * 40, 0, i * 40, 400);
				g.drawLine((i + 5) * 40, 0, (i + 5) * 40, 400);
				g.drawLine((i + 10) * 40, 0, (i + 10) * 40, 400);

				// horizontal
				g.drawLine(0, i * 40, 607, i * 40);
				g.drawLine(0, (i + 5) * 40, 607, (i + 5) * 40);

			}

			// put players
			g.drawImage(one.getImgPlayer(), one.getPosX(), one.getPosY(), null);
			g.drawImage(two.getImgPlayer(), two.getPosX(), two.getPosY(), null);

			// scores
				// win
			g.setColor(Color.green);
			g.drawString(one.getScore().getWin() + "W", getWidth() / 10 - 45, 420);
			g.drawString(two.getScore().getWin() + "W", getWidth() * 9 / 10 - 45, 420);
				// draw
			g.setColor(Color.black);
			g.drawString(one.getScore().getDraw() + "D", getWidth() / 10 - 10, 420);
			g.drawString(two.getScore().getDraw() + "D", getWidth() * 9 / 10 - 10, 420);
				// last update
			g.drawString(one.getScore().getDate(), 5, 450);
			g.drawString(two.getScore().getDate(), getWidth() * 8 / 10 - 10, 450);
				// lose
			g.setColor(Color.red);
			g.drawString(one.getScore().getLose() + "L", getWidth() / 10 + 25, 420);
			g.drawString(two.getScore().getLose() + "L", getWidth() * 9 / 10 + 25, 420);

		}
		// tells who won
		else if (endGame != 0) {
			switch (endGame) {
			case 1: // one won
				g.drawImage(one.getImg(5), 0, 0, 600, 400, null);		
				break;
			case 2: // two won
				g.drawImage(two.getImg(5), 0, 0, 600, 400, null);
				break;
			case 3: // Draw
				g.drawImage(one.getImg(6), 0, 0, 300, 400, null);
				g.drawImage(two.getImg(7), 300, 0, 300, 400, null);
				break;
			default:
				break;
			}

		}
		// display players and walls
		else {

			// display the new position of players
			g.drawImage(two.getImgPlayer(), two.getPosX(), two.getPosY(), null);
			g.drawImage(one.getImgPlayer(), one.getPosX(), one.getPosY(), null);

			// display only the latest walls
			// shorter calls
			Wall wallOne = one.getWall().get(one.getWall().size() - 1);
			Wall wallTwo = two.getWall().get(two.getWall().size() - 1);

			g.drawImage(wallOne.getImg(), wallOne.getPosX(), wallOne.getPosY(), null);
			g.drawImage(wallTwo.getImg(), wallTwo.getPosX(), wallTwo.getPosY(), null);

		}

	}

	public boolean isSetup() {
		return setup;
	}

	public void setSetup(boolean setup) {
		this.setup = setup;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getEndGame() {
		return endGame;
	}

	public void setEndGame(int endGame) {
		this.endGame = endGame;
	}

}
