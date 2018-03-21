package pl.wasowski.breakthrough.game.player;

import java.util.ArrayList;

import pl.wasowski.breakthrough.game.GameBoard;

public class RandomComputerPlayer extends Player {	// Rozszerza Gracza, tote¿ dziedziczy

	public RandomComputerPlayer(PlayerColour colour, GameBoard board) {	// Konstruktor z dziedziczeniem
		super(colour, board);
	}

	@Override
	public boolean makeMove() {
		ArrayList<String> ownedTilesCoordinates = new ArrayList<>(); // Tworzymy liste pionków
		for (int i = 0; i < GameBoard.ONE2EIGHT.length(); i++) {	// Petle przeszukujace
			for (int j = 0; j < GameBoard.A2H.length(); j++) {	// Petle przeszukujace
				String coordinates = board.getCoordinates(i, j);	
				if (board.getGameBoard().get(coordinates).getContent() == colour.getSymbol()) { // Dodajemy wspl gdy zawieraja kolor gracza
					ownedTilesCoordinates.add(coordinates);
				}

			}
		}
		int randomMultiplicator = 10000; 
		double random = Math.random(); // Generator losowy
		int randomIndex = (int) (random * randomMultiplicator) % ownedTilesCoordinates.size(); // Liczymy losowy index
		for (int i = -1; i < 2; i++) {											// 3 Mozliwosci ruchu (kierunki)
			String startingCoordinates = ownedTilesCoordinates.get(randomIndex); // Losowy wybór pionka
			char firstChar = startingCoordinates.charAt(0); // Zdobywanie wspl kolumna, np A
			char secondChar = startingCoordinates.charAt(1); // Zdobywanie wspl wiersz, np 2
			char firstCharModified = (char) (firstChar + i);  // Modelowanie zmiany wspl.
			char secondCharModified = '0';						// Inicjalizacja
			if (this.getColour().equals(PlayerColour.WHITE)) {
				secondCharModified = (char) (secondChar + 1); // Modelowanie kierunku zmiany wspl
			} else {
				secondCharModified = (char) (secondChar - 1);
			}

			if (board.makeMove(startingCoordinates, String.valueOf(firstCharModified) + secondCharModified, colour)) {	// Wykonanie ruchu
				return true;
			}
		}
		return false;
	}

}
