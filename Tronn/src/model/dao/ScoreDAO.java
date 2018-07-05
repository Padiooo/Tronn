package model.dao;

public class ScoreDAO {

	private String color;
	private String win;
	private String draw;
	private String lose;
	private String date;

	public ScoreDAO(String data) {

		String delims = "[ ]+";
		String[] dataSplited = data.split(delims);
		this.color = dataSplited[0];
		this.win = dataSplited[1];
		this.draw = dataSplited[2];
		this.lose = dataSplited[3];
		this.date = dataSplited[4] + " " + dataSplited[5];
	}

	public String getColor() {
		return color;
	}

	public String getWin() {
		return win;
	}

	public String getDraw() {
		return draw;
	}

	public String getLose() {
		return lose;
	}

	public String getDate() {
		return date;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public void setLose(String lose) {
		this.lose = lose;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
