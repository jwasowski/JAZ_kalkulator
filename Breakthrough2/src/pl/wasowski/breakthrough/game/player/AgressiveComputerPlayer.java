package pl.wasowski.breakthrough.game.player;

import java.util.ArrayList;

import pl.wasowski.breakthrough.game.GameBoard;

public class AgressiveComputerPlayer extends RandomComputerPlayer { // Rozszerza RandomCpl aby korzystac z metody ruchu losowego

	public AgressiveComputerPlayer(PlayerColour colour, GameBoard board) {
		super(colour, board);
	}

	@Override
	public boolean makeMove() {
		ArrayList<String> ownedTilesCoordinates = new ArrayList<>(); // Tworzymy liste pionków
		for (int i = 0; i < GameBoard.ONE2EIGHT.length(); i++) {
			for (int j = 0; j < GameBoard.A2H.length(); j++) {
				String coordinates = board.getCoordinates(i, j);
				if (board.getGameBoard().get(coordinates).getContent() == colour.getSymbol()) {
					ownedTilesCoordinates.add(coordinates);
				}
			}
		}
		String destinationCoordinates = null; 
		String originalCoordinates = null;
		for (String coordinates : ownedTilesCoordinates) { // Do wspl przypisujemy kolejne wspl posiadanych pionkow
			char firstChar = coordinates.charAt(0);		
			char secondChar = coordinates.charAt(1);
			char firstCharModified;
			char secondCharModified;
			String newCoordinates;
			String tileContent;

			if (this.getColour().equals(PlayerColour.WHITE)) { // Modelowanie kierunku ruchu (bicia)
				firstCharModified = (char) (firstChar - 1);
				secondCharModified = (char) (secondChar + 1);
			} else {
				firstCharModified = (char) (firstChar - 1);
				secondCharModified = (char) (secondChar - 1);
			}

			if (GameBoard.A2H.contains(String.valueOf(firstCharModified))
					&& GameBoard.ONE2EIGHT.contains(String.valueOf(secondCharModified))) {
				newCoordinates = String.valueOf(firstCharModified) + secondCharModified; // Budujemy wspl nowego ruchu
				tileContent = board.getGameBoard().get(newCoordinates).getContent(); // Zdobycie zawartosci wspl nowego ruchu
				if (tileContent.equals(getOpposingPlayerSymbol())) {	// Sprawdzenie czy jest tam przeciwnik
					originalCoordinates = coordinates;
					destinationCoordinates = newCoordinates;
					break;
				}
			}

			if (this.getColour().equals(PlayerColour.WHITE)) { // Modelowanie kierunku ruchu (bicia)
				firstCharModified = (char) (firstChar + 1);
				secondCharModified = (char) (secondChar + 1);
			} else {
				firstCharModified = (char) (firstChar + 1);
				secondCharModified = (char) (secondChar - 1);
			}

			if (GameBoard.A2H.contains(String.valueOf(firstCharModified))
					&& GameBoard.ONE2EIGHT.contains(String.valueOf(secondCharModified))) {

				newCoordinates = String.valueOf(firstCharModified) + secondCharModified;	
				tileContent = board.getGameBoard().get(newCoordinates).getContent();
				if (tileContent.equals(getOpposingPlayerSymbol())) {
					originalCoordinates = coordinates;
					destinationCoordinates = newCoordinates;
					break;
				}
			}

		}

		if (originalCoordinates == null || destinationCoordinates == null) { // Nie ma bicia to losowy ruch
			return super.makeMove();
		} else {
			return board.makeMove(originalCoordinates, destinationCoordinates, colour); // Wykonaj ruch
		}

	}

	private String getOpposingPlayerSymbol() { // Metoda do sprawdzania kto jest przeciwnikiem
		if (this.getColour().equals(PlayerColour.WHITE)) {
			return PlayerColour.BLACK.getSymbol();
		} else {
			return PlayerColour.WHITE.getSymbol();
		}
	}
}
