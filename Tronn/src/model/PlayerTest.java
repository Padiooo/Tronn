package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.EnumDirection;
import model.Player;

class PlayerTest {

	private static Player playerOneTest;
	private static Player playerTwoTest;
	private Player playerTest;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		playerOneTest = new Player(null, "red", 1, null);
		playerTwoTest = new Player(null, "blue", 2, null);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		playerTest = new Player(null, "red", 1, null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/********* Player() *********/

	@Test
	void building_A_Player_One_Checking_Position_And_Direction() {

		Player playerExpected = new Player(null, "red", 1, null);
		playerExpected.setDirection(EnumDirection.Right);
		playerExpected.setX(1);
		playerExpected.setY(7);
		playerExpected.setPosX();
		playerExpected.setPosY();

		assertEquals(playerExpected.getPosX(), playerOneTest.getPosX());
		assertEquals(playerExpected.getPosY(), playerOneTest.getPosY());
		assertEquals(playerExpected.getX(), playerOneTest.getX());
		assertEquals(playerExpected.getY(), playerOneTest.getY());
		assertEquals(playerExpected.getDirection(), playerOneTest.getDirection());
	}

	@Test
	void building_A_Player_Two_Checking_Position_And_Direction() {

		Player playerExpected = new Player(null, "blue", 2, null);
		playerExpected.setDirection(EnumDirection.Left);
		playerExpected.setX(13);
		playerExpected.setY(2);
		playerExpected.setPosX();
		playerExpected.setPosY();

		assertEquals(playerExpected.getPosX(), playerTwoTest.getPosX());
		assertEquals(playerExpected.getPosY(), playerTwoTest.getPosY());
		assertEquals(playerExpected.getX(), playerTwoTest.getX());
		assertEquals(playerExpected.getY(), playerTwoTest.getY());
		assertEquals(playerExpected.getDirection(), playerTwoTest.getDirection());
	}

	/********* move() *********/

	@Test
	void given_A_Direction_UP_Move_Checking_New_Position() {

		int yExpected = 0;

		playerTest.setDirection(EnumDirection.Up);
		yExpected = playerTest.getY() - 1;
		playerTest.move();
		assertEquals(yExpected, playerTest.getY());
	}

	@Test
	void given_A_Direction_RIGHT_Move_Checking_New_Position() {

		int xExpected = 0;

		playerTest.setDirection(EnumDirection.Right);
		xExpected = playerTest.getX() + 1;
		playerTest.move();
		assertEquals(xExpected, playerTest.getX());

	}

	@Test
	void given_A_Direction_DOWN_Move_Checking_New_Position() {

		int yExpected = 0;

		playerTest.setDirection(EnumDirection.Down);
		yExpected = playerTest.getY() + 1;
		playerTest.move();
		assertEquals(yExpected, playerTest.getY());

	}

	@Test
	void given_A_Direction_LEFT_Move_Checking_New_Position() {

		int xExpected = 0;

		playerTest.setDirection(EnumDirection.Left);
		xExpected = playerTest.getX() - 1;
		playerTest.move();
		assertEquals(xExpected, playerTest.getX());

	}

	/********* changeDirection() *********/
	@Test
	void given_A_Key1_And_UP_Change_Direction_Checking_New_Direction() {

		EnumDirection RIGHT = EnumDirection.Right;
		int key = 1;
		playerTest.setCurrentDirection(0);
		playerTest.setDirection();
		playerTest.changeDirection(key);

		assertEquals(RIGHT, playerTest.getDirection());

	}

	@Test
	void given_A_Key1_And_RIGHT_Change_Direction_Checking_New_Direction() {

		EnumDirection DOWN = EnumDirection.Down;
		int key = 1;
		playerTest.setCurrentDirection(1);
		playerTest.setDirection();
		playerTest.changeDirection(key);

		assertEquals(DOWN, playerTest.getDirection());

	}

	@Test
	void given_A_Key1_And_DOWN_Change_Direction_Checking_New_Direction() {

		EnumDirection LEFT = EnumDirection.Left;
		int key = 1;
		playerTest.setCurrentDirection(2);
		playerTest.setDirection();
		playerTest.changeDirection(key);

		assertEquals(LEFT, playerTest.getDirection());

	}

	@Test
	void given_A_Key1_And_LEFT_Change_Direction_Checking_New_Direction() {

		EnumDirection UP = EnumDirection.Up;
		int key = 1;
		playerTest.setCurrentDirection(3);
		playerTest.setDirection();
		playerTest.changeDirection(key);

		assertEquals(UP, playerTest.getDirection());

	}

}
