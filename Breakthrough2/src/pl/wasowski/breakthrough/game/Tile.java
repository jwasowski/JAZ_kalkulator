package pl.wasowski.breakthrough.game;

public class Tile {
	private String content;

	Tile(String content) {
		this.content = content;
	}

	void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}