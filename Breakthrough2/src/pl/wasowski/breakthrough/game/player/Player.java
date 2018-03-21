package pl.wasowski.breakthrough.game.player;

import pl.wasowski.breakthrough.game.GameBoard;

public abstract class Player { // Klasa w której opisujemy gracza (z czego ma sie skladac)
	PlayerColour colour;
	GameBoard board;

	public Player(PlayerColour colour, GameBoard board) { // Konstruktor
		this.colour = colour;
		this.board = board;
	}

	PlayerColour getColour() { // Metoda przyporzadkowania koloru
		return colour;
	}

	public abstract boolean makeMove();	// Metoda wykonania ruchu
}
