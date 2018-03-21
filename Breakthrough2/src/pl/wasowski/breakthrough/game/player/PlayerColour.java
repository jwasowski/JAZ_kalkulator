package pl.wasowski.breakthrough.game.player;

public enum PlayerColour { // Typ wyliczeniowy zawierajacy dwa mozliwe kolory wraz z metodami zdobycia symboli  
	WHITE("W"), BLACK("B");
	private String symbol;

	PlayerColour(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}
