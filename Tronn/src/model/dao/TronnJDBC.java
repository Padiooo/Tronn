package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */
public final class TronnJDBC {
	/** The instance. */
	private static TronnJDBC instance;
	/** The login. */
	private static String user = "root";
	/** The password. */
	private static String password = "root";
	/** The url. */
	private static String url = "jdbc:mysql://localhost/tronn?useSSL=false&serverTimezone=UTC";
	/** The connection. */
	private Connection connection;
	/** The statement. */
	private Statement statement;

	/**
	 * Instantiates a new boulder dash BDD connector.
	 */
	private TronnJDBC() {
		this.open();
	}

	/**
	 * Gets the single instance of BoulderDashBDDConnector.
	 *
	 * @return single instance of BoulderDashBDDConnector
	 */
	public static TronnJDBC getInstance() {
		if (instance == null) {
			setInstance(new TronnJDBC());
		}
		return instance;
	}

	/**
	 * Sets the instance.
	 *
	 * @param instance
	 *            the new instance
	 */
	private static void setInstance(final TronnJDBC instance) {
		TronnJDBC.instance = instance;
	}

	/**
	 * Open.
	 *
	 * @return true, if successful
	 */
	private boolean open() {
		try {
			this.connection = DriverManager.getConnection(TronnJDBC.url, TronnJDBC.user, TronnJDBC.password);
			this.statement = this.connection.createStatement();
			return true;
		} catch (final SQLException exception) {
			System.out.println("Failure connection to Database");
			System.exit(0);
		}
		return false;
	}

	// colorScore(String color) called by the model to get
	// stat of the color player
	// execute a stored procedure in the database
	// "CALL SelectColor(IN color VARCHAR(10))"
	// return all stats in a string splitted with " "
	public String colorScore(String color) {

		ResultSet result;
		ResultSetMetaData resultMeta = null;
		String re = "";
		try {
			result = statement.executeQuery("CALL SelectColor(\"" + color + "\")");
			resultMeta = result.getMetaData();

			while (result.next()) {
				for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
					re += String.valueOf(result.getObject(i)) + " ";
				}
			}
		} catch (Exception e) {
			System.out.println("Failure reading data");
			System.exit(0);
		}
		return re;
	}

	// colorScore(String color) called by the model 
	// update number of win of the color player
	// execute a stored procedure in the database
	// "CALL UpdateWin(IN color VARCHAR(10))"
	@SuppressWarnings("unused")
	public void updateWinColor(String color) {
			try {
				ResultSet result = statement.executeQuery("CALL UpdateWin(\"" + color + "\")");
			} catch (SQLException e) {
				System.out.println("Failure updating win for : " + color);
				System.exit(0);
			}
	}

	// colorScore(String color) called by the model 
	// update number of win of the color player
	// execute a stored procedure in the database
	// "CALL UpdateLose(IN color VARCHAR(10))"
	@SuppressWarnings("unused")
	public void updateLoseColor(String color) {
			try {
				ResultSet result = statement.executeQuery("CALL UpdateLose(\"" + color + "\")");
			} catch (SQLException e) {
				System.out.println("Failure updating lose for : " + color);
				System.exit(0);
			}
	}

	// colorScore(String color) called by the model 
	// update number of draw of both players
	// execute a stored procedure in the database
	// "CALL UpdateDraw(IN color1 VARCHAR(10), IN color2 VARCHAR(10))"
	@SuppressWarnings("unused")
	public void updateDraw(String colorOne, String colorTwo) {
			try {
				ResultSet result = statement.executeQuery("CALL UpdateDraw(\""+colorOne+"\",\""+colorTwo+"\")");
			} catch (SQLException e) {
				System.out.println("Failure updating draw for : " + colorOne + " and " + colorTwo);
				System.exit(0);
			}
	}

	/************ Normal Getters and Setters ************/

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(final Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return this.statement;
	}

	public void setStatement(final Statement statement) {
		this.statement = statement;
	}

}
